package cn.lzxz1234.weixin.api.wx.vo.request;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class ServiceVideoRequest implements Serializable {

    private static final long serialVersionUID = 6671832217175547582L;

    @JSONField(name = "touser") private String touser;
    @JSONField(name = "msgtype") private String msgtype = "video";
    @JSONField(name = "video") private Video video;
    
    public ServiceVideoRequest() {
        
    }
    
    public ServiceVideoRequest(String touser, String mediaId, String thumbMediaId, String title,
                String description) {
        
        this.touser = touser;
        this.video = new Video(mediaId, thumbMediaId, title, description);
    }
    
    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public static class Video implements Serializable {
        
        private static final long serialVersionUID = -139695807312902754L;
        
        @JSONField(name = "media_id") private String mediaId;
        @JSONField(name = "thumb_media_id") private String thumbMediaId;
        @JSONField(name = "title") private String title;
        @JSONField(name = "description") private String description;
        
        public Video() {
            
        }
        
        public Video(String mediaId, String thumbMediaId, String title,
                String description) {
            super();
            this.mediaId = mediaId;
            this.thumbMediaId = thumbMediaId;
            this.title = title;
            this.description = description;
        }

        public String getMediaId() {
            return mediaId;
        }
        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }
        public String getThumbMediaId() {
            return thumbMediaId;
        }
        public void setThumbMediaId(String thumbMediaId) {
            this.thumbMediaId = thumbMediaId;
        }
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
        
    }
    
}
