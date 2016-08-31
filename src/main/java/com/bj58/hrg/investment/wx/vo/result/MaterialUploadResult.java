package com.bj58.hrg.investment.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

public class MaterialUploadResult extends BasicResult {

    private static final long serialVersionUID = 4343833826563814819L;
    
    @JSONField(name="url") private String url;
    @JSONField(name="type") private String type;
    @JSONField(name="media_id") private String mediaId;
    @JSONField(name="created_at") private long createdAt;
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMediaId() {
        return mediaId;
    }
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    public long getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
    
}
