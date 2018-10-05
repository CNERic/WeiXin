package cn.lzxz1234.weixin.api.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

public class PlatFormAccessTokenResult extends BasicResult {

    private static final long serialVersionUID = -8773531681245605512L;
    
    @JSONField(name="component_access_token") private String componentAccessToken;
    @JSONField(name="expires_in") private Long expiresIn;
    
    public String getComponentAccessToken() {
        return componentAccessToken;
    }
    public void setComponentAccessToken(String componentAccessToken) {
        this.componentAccessToken = componentAccessToken;
    }
    public Long getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
    
}
