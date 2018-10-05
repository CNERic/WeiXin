package cn.lzxz1234.weixin.api.wx.vo.request;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class PlatformRefreshAccessTokenRequest implements Serializable {

    private static final long serialVersionUID = -6808796044033244480L;

    @JSONField(name = "component_appid") private String componentAppid;
    @JSONField(name = "authorizer_appid") private String authorizerAppid;
    @JSONField(name = "authorizer_refresh_token") private String authorizerRefreshToken;
    
    public PlatformRefreshAccessTokenRequest() {
        
    }
    
    public PlatformRefreshAccessTokenRequest(String componentAppid, String authorizerAppid, String authorizerRefreshToken) {
        
        this.componentAppid = componentAppid;
        this.authorizerAppid = authorizerAppid;
        this.authorizerRefreshToken = authorizerRefreshToken;
    }
    
    public String getComponentAppid() {
        return componentAppid;
    }
    public void setComponentAppid(String componentAppid) {
        this.componentAppid = componentAppid;
    }
    public String getAuthorizerAppid() {
        return authorizerAppid;
    }
    public void setAuthorizerAppid(String authorizerAppid) {
        this.authorizerAppid = authorizerAppid;
    }
    public String getAuthorizerRefreshToken() {
        return authorizerRefreshToken;
    }
    public void setAuthorizerRefreshToken(String authorizerRefreshToken) {
        this.authorizerRefreshToken = authorizerRefreshToken;
    }
}
