package cn.lzxz1234.weixin.api.wx;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lzxz1234
 * @version v1.0
 * @class WeiXinProperties
 */
@ConfigurationProperties("cn.lzxz1234.weixin")
public class WeiXinProperties {

    private String id;
    private String secret;
    private String aeskey;
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAeskey() {
        return aeskey;
    }

    public void setAeskey(String aeskey) {
        this.aeskey = aeskey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
