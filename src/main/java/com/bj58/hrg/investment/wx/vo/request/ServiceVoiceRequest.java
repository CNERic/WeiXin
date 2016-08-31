package com.bj58.hrg.investment.wx.vo.request;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class ServiceVoiceRequest implements Serializable {

    private static final long serialVersionUID = -2695977859282491519L;
    
    @JSONField(name = "touser") private String touser;
    @JSONField(name = "msgtype") private String msgtype = "voice";
    @JSONField(name = "voice") private Voice voice;
    
    public ServiceVoiceRequest() {
        
    }
    
    public ServiceVoiceRequest(String touser, String mediaId) {
        
        this.touser = touser;
        this.voice = new Voice(mediaId);
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

    public static class Voice implements Serializable {
     
        private static final long serialVersionUID = -8837620146599673881L;
        
        @JSONField(name = "media_id") private String mediaId;

        public Voice() {
            
        }
        
        public Voice(String mediaId) {
            
            this.mediaId = mediaId;
        }
        
        public String getMediaId() {
            return mediaId;
        }
        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }
        
    }
    
}
