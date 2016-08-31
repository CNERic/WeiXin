package com.bj58.hrg.investment.wx.vo.message;

/**
 * @class VideoMessage
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class VideoMessage extends Message {

    private String MediaId;     //视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String ThumbMediaId;//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
    
    private String videoLocation;
    private String videoThumbLocation;
    
    public String getMediaId() {
        return MediaId;
    }
    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
    public String getThumbMediaId() {
        return ThumbMediaId;
    }
    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
    public String getVideoLocation() {
        return videoLocation;
    }
    public void setVideoLocation(String videoLocation) {
        this.videoLocation = videoLocation;
    }
    public String getVideoThumbLocation() {
        return videoThumbLocation;
    }
    public void setVideoThumbLocation(String videoThumbLocation) {
        this.videoThumbLocation = videoThumbLocation;
    }
    
}
