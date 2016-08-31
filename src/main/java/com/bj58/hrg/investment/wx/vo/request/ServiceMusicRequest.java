package com.bj58.hrg.investment.wx.vo.request;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class ServiceMusicRequest implements Serializable {

    private static final long serialVersionUID = 8378433946867261570L;

    @JSONField(name = "touser") private String touser;
    @JSONField(name = "msgtype") private String msgtype = "music";
    @JSONField(name = "music") private Music music;
    
    public ServiceMusicRequest(String touser, String title, String description, String musicurl,
            String hqmusicurl, String thumbMediaId) {
        
        this.touser = touser;
        this.music = new Music(title, description, musicurl, hqmusicurl, thumbMediaId);
    }
    
    public ServiceMusicRequest() {
        
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

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public static class Music implements Serializable {
        
        private static final long serialVersionUID = -4993095949883862209L;
        
        @JSONField(name = "title") private String title;
        @JSONField(name = "description") private String description;
        @JSONField(name = "musicurl") private String musicurl;
        @JSONField(name = "hqmusicurl") private String hqmusicurl;
        @JSONField(name = "thumb_media_id") private String thumbMediaId;
        
        public Music() {
            
        }
        
        public Music(String title, String description, String musicurl,
                String hqmusicurl, String thumbMediaId) {
            super();
            this.title = title;
            this.description = description;
            this.musicurl = musicurl;
            this.hqmusicurl = hqmusicurl;
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
        public String getMusicurl() {
            return musicurl;
        }
        public void setMusicurl(String musicurl) {
            this.musicurl = musicurl;
        }
        public String getHqmusicurl() {
            return hqmusicurl;
        }
        public void setHqmusicurl(String hqmusicurl) {
            this.hqmusicurl = hqmusicurl;
        }
        public String getThumbMediaId() {
            return thumbMediaId;
        }
        public void setThumbMediaId(String thumbMediaId) {
            this.thumbMediaId = thumbMediaId;
        }
        
    }
}
