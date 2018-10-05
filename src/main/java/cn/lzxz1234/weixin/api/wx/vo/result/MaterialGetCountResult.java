package cn.lzxz1234.weixin.api.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

public class MaterialGetCountResult extends BasicResult {

    private static final long serialVersionUID = -4533588666297774633L;

    @JSONField(name="voice_count") private int voiceCount;
    @JSONField(name="video_count") private int videoCount;
    @JSONField(name="image_count") private int imageCount;
    @JSONField(name="news_count") private int newsCount;
    
    public int getVoiceCount() {
        return voiceCount;
    }
    public void setVoiceCount(int voiceCount) {
        this.voiceCount = voiceCount;
    }
    public int getVideoCount() {
        return videoCount;
    }
    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }
    public int getImageCount() {
        return imageCount;
    }
    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }
    public int getNewsCount() {
        return newsCount;
    }
    public void setNewsCount(int newsCount) {
        this.newsCount = newsCount;
    }
    
}
