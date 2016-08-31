package com.bj58.hrg.investment.wx.vo.message;

/**
 * @class ImageMessage
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class ImageMessage extends Message {

    private String PicUrl; //图片链接
    private String MediaId;//图片消息媒体id，可以调用多媒体文件下载接口拉取数据
    
    private String picLocation;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getPicLocation() {
        return picLocation;
    }

    public void setPicLocation(String picLocation) {
        this.picLocation = picLocation;
    }
    
}
