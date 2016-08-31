package com.bj58.hrg.investment.wx.vo.request;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class PlatformSetAuthorizerOptionRequest implements Serializable {

    private static final long serialVersionUID = 1554214258361478603L;
    
    @JSONField(name = "component_appid") private String componentAppid;
    @JSONField(name = "authorizer_appid") private String authorizerAppid;
    @JSONField(name = "option_name") private String optionName;
    @JSONField(name = "option_value") private String optionValue;
    
    public PlatformSetAuthorizerOptionRequest() {
        
    }
    
    public PlatformSetAuthorizerOptionRequest(String componentAppid, 
            String authorizerAppid, String optionName, String optionValue) {
        
        this.componentAppid = componentAppid;
        this.authorizerAppid = authorizerAppid;
        this.optionName = optionName;
        this.optionValue = optionValue;
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
    public String getOptionValue() {
        return optionValue;
    }
    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }
    
}
