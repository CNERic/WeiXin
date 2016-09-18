package com.bj58.hrg.investment.wx.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class MaterialArticle implements Serializable {

    private static final long serialVersionUID = -6214979285289305376L;
    
    /**
     * 标题
     */
    @JSONField(name="title") private String title;
    /**
     * 图文消息的封面图片素材id（必须是永久mediaID）
     */
    @JSONField(name="thumb_media_id") private String thumbMediaId;
    /**
     * 作者
     */
    @JSONField(name="author") private String author;
    /**
     * 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
     */
    @JSONField(name="digest") private String digest;
    /**
     * 是否显示封面，0为false，即不显示，1为true，即显示
     */
    @JSONField(name="show_cover_pic") private String showCoverPic;
    /**
     * 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
     */
    @JSONField(name="content") private String content;
    /**
     * 图文页的URL
     */
    @JSONField(name="url") private String url;
    /**
     * 图文消息的原文地址，即点击“阅读原文”后的URL
     */
    @JSONField(name="content_source_url") private String contentSourceUrl;
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getThumbMediaId() {
        return thumbMediaId;
    }
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getDigest() {
        return digest;
    }
    public void setDigest(String digest) {
        this.digest = digest;
    }
    public String getShowCoverPic() {
        return showCoverPic;
    }
    public void setShowCoverPic(String showCoverPic) {
        this.showCoverPic = showCoverPic;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getContentSourceUrl() {
        return contentSourceUrl;
    }
    public void setContentSourceUrl(String contentSourceUrl) {
        this.contentSourceUrl = contentSourceUrl;
    }
    
}
