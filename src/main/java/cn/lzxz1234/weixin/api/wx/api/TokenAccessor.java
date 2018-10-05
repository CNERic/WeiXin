package cn.lzxz1234.weixin.api.wx.api;

import cn.lzxz1234.weixin.api.common.Assert;
import cn.lzxz1234.weixin.api.common.HttpUtils;
import cn.lzxz1234.weixin.api.common.StringTemplate;
import cn.lzxz1234.weixin.api.wx.clust.Synchronizer;
import cn.lzxz1234.weixin.api.wx.dto.App;
import cn.lzxz1234.weixin.api.wx.vo.result.AccessTokenResult;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 公众号 AccessToken 管理器
 * @class TokenAccessor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class TokenAccessor {

    private static final Logger log = Logger.getLogger(TokenAccessor.class);
    private static final StringTemplate accessTokenUrl = StringTemplate.compile(WeiXinURL.GET_TOKEN);
    private Synchronizer synchronizer;
    private AccessTokenResult result;

    public TokenAccessor(Synchronizer synchronizer) {

        this.synchronizer = synchronizer;
    }
    
    public String getAccessToken() {
        
        if(result == null || System.currentTimeMillis() > result.getExpiresIn()) {
            try {
                synchronizer.lock(getTokenKey());
                result = synchronizer.get(AccessTokenResult.class, getTokenKey());
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
                    synchronizer.set(getTokenKey(), result);
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
