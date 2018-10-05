package cn.lzxz1234.weixin.api.wx.vo.request;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class PlatformGetAccessToken implements Serializable {

    private static final long serialVersionUID = 6192034113964360401L;

    @JSONField(name = "component_appid") private String componentAppid;
    @JSONField(name = "component_appsecret") private String componentAppsecret;
    @JSONField(name = "component_verify_ticket") private String componentVerifyTicket;
    
    public PlatformGetAccessToken() {
        
    }
    
    public PlatformGetAccessToken(String appId, String appSecret, String verifyTicket) {
        
        this.componentAppid = appId;
        this.componentAppsecret = appSecret;
        this.componentVerifyTicket = verifyTicket;
    }
    
    public String getComponentAppid() {
        return componentAppid;
    }
    public void setComponentAppid(String componentAppid) {
        this.componentAppid = componentAppid;
    }
    public String getComponentAppsecret() {
        return componentAppsecret;
    }
    public void setComponentAppsecret(String componentAppsecret) {
        this.componentAppsecret = componentAppsecret;
    }
    public String getComponentVerifyTicket() {
        return componentVerifyTicket;
    }
    public void setComponentVerifyTicket(String componentVerifyTicket) {
        this.componentVerifyTicket = componentVerifyTicket;
    }
    
}
