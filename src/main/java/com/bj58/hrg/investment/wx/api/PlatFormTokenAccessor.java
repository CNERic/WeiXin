package com.bj58.hrg.investment.wx.api;

import com.alibaba.fastjson.JSON;
import com.bj58.hrg.investment.common.HttpUtils;
import com.bj58.hrg.investment.wx.annotation.Autowired;
import com.bj58.hrg.investment.wx.annotation.Singleton;
import com.bj58.hrg.investment.wx.clust.Synchronizer;
import com.bj58.hrg.investment.wx.dto.App;
import com.bj58.hrg.investment.wx.vo.request.PlatformGetAccessToken;
import com.bj58.hrg.investment.wx.vo.result.PlatFormAccessTokenResult;

/**
 * 公众号第三方公众平台的 AccessToken 管理器
 * @class PlatFormTokenAccessor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Singleton
public class PlatFormTokenAccessor {
    
    private static final String platformTokenUrl = WeiXinURL.PLATFORM_GET_ACCESSTOKEN;

    @Autowired("lock")
    private Synchronizer synchronizer;
    private String componentVerifyTicket;
    private PlatFormAccessTokenResult result;
    
    public String getAccessToken() {
        
        if(result == null || System.currentTimeMillis() > result.getExpiresIn()) {
            try {
                synchronizer.lock(getTokenKey());
                result = synchronizer.globalGet(PlatFormAccessTokenResult.class, getTokenKey());
                if(result == null || System.currentTimeMillis() > result.getExpiresIn()) {
                    String postJson = JSON.toJSONString(new PlatformGetAccessToken(App.Info.id, App.Info.secret, componentVerifyTicket));
                    String respJson = HttpUtils.post(platformTokenUrl, postJson);
                    result = JSON.parseObject(respJson, PlatFormAccessTokenResult.class);
                    result.setExpiresIn(System.currentTimeMillis() + result.getExpiresIn() * 900);
                    synchronizer.globalSet(getTokenKey(), result);
                }
            } finally {
                synchronizer.unlock(getTokenKey());
            }
        }
        return result.getComponentAccessToken();
    }
    
    private String getTokenKey() {
        
        return "PlatFormTokenAccessor-" + App.Info.id;
    }

    public void updatePlatFormVerifyTicket(String componentVerifyTicket) {
        
        this.componentVerifyTicket = componentVerifyTicket;
    }
    
}
