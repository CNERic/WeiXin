package cn.lzxz1234.weixin.api.wx.api;

import cn.lzxz1234.weixin.api.common.HttpUtils;
import cn.lzxz1234.weixin.api.wx.clust.Synchronizer;
import cn.lzxz1234.weixin.api.wx.dto.App;
import cn.lzxz1234.weixin.api.wx.vo.request.PlatformGetAccessToken;
import cn.lzxz1234.weixin.api.wx.vo.result.PlatFormAccessTokenResult;
import com.alibaba.fastjson.JSON;

/**
 * 公众号第三方公众平台的 AccessToken 管理器
 * @class PlatFormTokenAccessor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class PlatFormTokenAccessor {
    
    private static final String platformTokenUrl = WeiXinURL.PLATFORM_GET_ACCESSTOKEN;

    private Synchronizer synchronizer;
    private String componentVerifyTicket;
    private PlatFormAccessTokenResult result;

    public PlatFormTokenAccessor(Synchronizer synchronizer) {

        this.synchronizer = synchronizer;
    }
    
    public String getAccessToken() {
        
        if(result == null || System.currentTimeMillis() > result.getExpiresIn()) {
            try {
                synchronizer.lock(getTokenKey());
                result = synchronizer.get(PlatFormAccessTokenResult.class, getTokenKey());
                if(result == null || System.currentTimeMillis() > result.getExpiresIn()) {
                    String postJson = JSON.toJSONString(new PlatformGetAccessToken(App.Info.id, App.Info.secret, componentVerifyTicket));
                    String respJson = HttpUtils.post(platformTokenUrl, postJson);
                    result = JSON.parseObject(respJson, PlatFormAccessTokenResult.class);
                    result.setExpiresIn(System.currentTimeMillis() + result.getExpiresIn() * 900);
                    synchronizer.set(getTokenKey(), result);
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
