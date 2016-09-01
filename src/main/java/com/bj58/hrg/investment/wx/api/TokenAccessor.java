package com.bj58.hrg.investment.wx.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.bj58.hrg.investment.common.Assert;
import com.bj58.hrg.investment.common.HttpUtils;
import com.bj58.hrg.investment.common.StringTemplate;
import com.bj58.hrg.investment.wx.annotation.Autowired;
import com.bj58.hrg.investment.wx.annotation.Singleton;
import com.bj58.hrg.investment.wx.clust.Synchronizer;
import com.bj58.hrg.investment.wx.dto.App;
import com.bj58.hrg.investment.wx.vo.result.AccessTokenResult;

/**
 * 公众号 AccessToken 管理器
 * @class TokenAccessor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Singleton
public class TokenAccessor {

    private static final Logger log = Logger.getLogger(TokenAccessor.class);
    private static final StringTemplate accessTokenUrl = StringTemplate.compile(WeiXinURL.GET_TOKEN);
    @Autowired("lock")
    private Synchronizer synchronizer;
    private AccessTokenResult result;
    
    public String getAccessToken() {
        
        if(result == null || System.currentTimeMillis() > result.getExpiresIn()) {
            try {
                synchronizer.lock(getTokenKey());
                result = synchronizer.globalGet(AccessTokenResult.class, getTokenKey());
                if(result == null || System.currentTimeMillis() > result.getExpiresIn()) {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("appId", App.Info.id);
                    params.put("appSecret", App.Info.secret);
                    String urlLocation = accessTokenUrl.replace(params);
                    String respJson = HttpUtils.get(urlLocation);
                    result = JSON.parseObject(respJson, AccessTokenResult.class);
                    Assert.notNull(result.getAccessToken(), "获取Token失败[%s][%s]", result.getErrcode(), result.getErrmsg());
                    //900 是 1000 毫秒的 0.9 倍，百分十的提前量更新 Token 
                    result.setExpiresIn(System.currentTimeMillis() + result.getExpiresIn() * 900);
                    log.info("更新AccessToken：" + result.getAccessToken());
                    synchronizer.globalSet(getTokenKey(), result);
                }
            } finally {
                synchronizer.unlock(getTokenKey());
            }
        }
        return result.getAccessToken();
    }
    
    private String getTokenKey() {
        
        return "TokenAccessor-" + App.Info.id;
    }

}
