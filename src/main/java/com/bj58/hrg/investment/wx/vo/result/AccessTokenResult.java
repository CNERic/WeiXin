package com.bj58.hrg.investment.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @class AccessTokenResult
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class AccessTokenResult extends BasicResult {

    private static final long serialVersionUID = -8150125867084144128L;
    
    @JSONField(name="access_token") private String accessToken;
    @JSONField(name="expires_in") private Long expiresIn;
    
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public Long getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
    
}
