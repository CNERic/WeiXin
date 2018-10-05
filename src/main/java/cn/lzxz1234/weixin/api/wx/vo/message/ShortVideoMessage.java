package cn.lzxz1234.weixin.api.wx.vo.message;

/**
 * @class VideoMessage
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class ShortVideoMessage extends Message {

    private String MediaId;     //视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String ThumbMediaId;//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
    
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

}
