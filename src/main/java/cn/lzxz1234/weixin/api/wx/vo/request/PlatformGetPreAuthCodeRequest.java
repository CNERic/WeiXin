package cn.lzxz1234.weixin.api.wx.vo.request;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class PlatformGetPreAuthCodeRequest implements Serializable {

    private static final long serialVersionUID = 5959474818831725092L;

    @JSONField(name = "component_appid") private String componentAppid;

    public PlatformGetPreAuthCodeRequest() {
        
    }
    
    public PlatformGetPreAuthCodeRequest(String appId) {
        
        this.componentAppid = appId;
    }
    
    public String getComponentAppid() {
        return componentAppid;
    }
    public void setComponentAppid(String componentAppid) {
        this.componentAppid = componentAppid;
    }
    
    
    
}
