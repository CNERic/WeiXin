package com.bj58.hrg.investment.wx.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.bj58.hrg.investment.wx.enums.ButtonType;

/**
 * @class Button
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class Button implements Serializable {

    private static final long serialVersionUID = 6406149723838730985L;
    
    @JSONField(name="type") private String type;
    @JSONField(name="name") private String name;
    @JSONField(name="key") private String key;
    @JSONField(name="url") private String url;
    @JSONField(name="media_id") private String mediaId;
    @JSONField(name="sub_button") private Button[] subButton;
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setType(ButtonType type) {
        this.type = type == null ? null : type.toString();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Button[] getSubButton() {
        return subButton;
    }
    public void setSubButton(Button[] subButton) {
        this.subButton = subButton;
    }
    public String getMediaId() {
        return mediaId;
    }
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    
}
