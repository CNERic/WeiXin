package cn.lzxz1234.weixin.api.wx.vo.request;

import com.alibaba.fastjson.annotation.JSONField;

public class MaterialDeleteRequest {

    @JSONField(name="media_id") private String mediaId;

    public MaterialDeleteRequest() {
        
    }
    
    public MaterialDeleteRequest(String mediaId) {
        
        this.mediaId = mediaId;
    }
    
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    
}
