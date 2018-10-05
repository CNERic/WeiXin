package cn.lzxz1234.weixin.api.wx.vo.request;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class PlatformGetAuthInfoRequest implements Serializable {

    private static final long serialVersionUID = 6113393947192863455L;

    @JSONField(name = "component_appid") private String componentAppid;
    @JSONField(name = "authorization_code") private String authorizationCode;
    
    public PlatformGetAuthInfoRequest() {
        
    }
    
    public PlatformGetAuthInfoRequest(String appId, String authCode) {
        
        this.componentAppid = appId;
        this.authorizationCode = authCode;
    }
    
    public String getComponentAppid() {
        return componentAppid;
    }
    public void setComponentAppid(String componentAppid) {
        this.componentAppid = componentAppid;
    }
    public String getAuthorizationCode() {
        return authorizationCode;
    }
    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }
    
}
