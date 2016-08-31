package com.bj58.hrg.investment.wx.vo.request;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class PlatformGetAuthorizerOptionRequest implements Serializable {

    private static final long serialVersionUID = 581672635883988495L;

    @JSONField(name = "component_appid") private String componentAppid;
    @JSONField(name = "authorizer_appid") private String authorizerAppid;
    @JSONField(name = "option_name") private String optionName;
    
    public PlatformGetAuthorizerOptionRequest() {
        
    }
    
    public PlatformGetAuthorizerOptionRequest(String componentAppid, String authorizerAppid, String optionName) {
        
        this.componentAppid = componentAppid;
        this.authorizerAppid = authorizerAppid;
        this.optionName = optionName;
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
    public String getOptionName() {
        return optionName;
    }
    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
    
}
