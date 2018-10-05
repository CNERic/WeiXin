package cn.lzxz1234.weixin.api.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @class OAuth2AccessTokenResult
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class OAuth2AccessTokenResult extends BasicResult {
    
    private static final long serialVersionUID = -899751390636415007L;
    
    @JSONField(name="access_token") private String accessToken;
    @JSONField(name="expires_in") private Integer expiresIn;
    @JSONField(name="refresh_token") private String refreshToken;
    @JSONField(name="openid") private String openId;
    @JSONField(name="scope") private String scope;
    @JSONField(name="unionid") private String unionId;
    
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public Integer getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public String getOpenId() {
        return openId;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    public String getUnionId() {
        return unionId;
    }
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
    
}
