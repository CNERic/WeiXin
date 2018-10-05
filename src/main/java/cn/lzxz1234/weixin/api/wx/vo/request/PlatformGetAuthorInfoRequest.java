package cn.lzxz1234.weixin.api.wx.vo.request;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class PlatformGetAuthorInfoRequest implements Serializable {

    private static final long serialVersionUID = 214327403034158110L;

    @JSONField(name = "component_appid") private String componentAppid;
    @JSONField(name = "authorizer_appid") private String authorizerAppid;
    
    public PlatformGetAuthorInfoRequest() {
        
    }
    public PlatformGetAuthorInfoRequest(String componentAppid, String authorizerAppid) {
        
        this.componentAppid = componentAppid;
        this.authorizerAppid = authorizerAppid;
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
    
}
