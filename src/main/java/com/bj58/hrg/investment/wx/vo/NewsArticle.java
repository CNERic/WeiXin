package com.bj58.hrg.investment.wx.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @class Article
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class NewsArticle implements Serializable {

    private static final long serialVersionUID = 2600429780865501695L;
    
    /**
     * 图文消息标题
     */
    @JSONField(name="title") private String title;
    /**
     * 图文消息描述
     */
    @JSONField(name="description") private String description;
    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     */
    @JSONField(name="picurl") private String picurl;
    /**
     * 点击图文消息跳转链接
     */
    @JSONField(name="url") private String url;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPicurl() {
        return picurl;
    }
    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    
}
