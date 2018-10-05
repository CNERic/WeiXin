package cn.lzxz1234.weixin.api.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

public class PlatFormGetPreAuthCodeResult extends BasicResult {

    private static final long serialVersionUID = 7492889147130582442L;
    
    @JSONField(name="pre_auth_code") private String preAuthCode;
    @JSONField(name="expires_in") private Long expiresIn;
    
    public String getPreAuthCode() {
        return preAuthCode;
    }
    public void setPreAuthCode(String preAuthCode) {
        this.preAuthCode = preAuthCode;
    }
    public Long getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
    
}
