package cn.lzxz1234.weixin.api.wx.listener.service;

import java.util.List;

import cn.lzxz1234.tencent.weixin.wx.annotation.Param;
import cn.lzxz1234.weixin.api.wx.template.PassiveMessage;
import cn.lzxz1234.weixin.api.wx.vo.NewsArticle;

public class Responsable {
    
    @Param
    protected String ToUserName;   //开发者微信号
    @Param protected String FromUserName; //发送方帐号（一个OpenID）
    @Param protected String CreateTime;   //消息创建时间 （整型）
    
    /**
     * @param content
     * @return
     */
    public String wrapText(String content) {
        
        return PassiveMessage.wrapText(FromUserName, ToUserName, content);
    }
    
    /**
     * @param mediaId 通过上传多媒体文件，得到的id
     * @return
     */
    public String wrapImage(String mediaId) {
        
        return PassiveMessage.wrapImage(FromUserName, ToUserName, mediaId);
    }
    
    /**
     * @param mediaId 通过上传多媒体文件，得到的id
     * @return
     */
    public String wrapVoice(String mediaId) {
        
        return PassiveMessage.wrapVoice(FromUserName, ToUserName, mediaId);
    }
    
    /**
     * @param mediaId 通过上传多媒体文件，得到的id
     * @return
     */
    public String wrapVideo(String mediaId) {
        
        return PassiveMessage.wrapVideo(FromUserName, ToUserName, mediaId);
    }
    
    /**
     * @param MediaId 通过上传多媒体文件，得到的id
     * @param Title 视频消息的标题
     * @param Description 视频消息的描述
     * @return
     */
    public String wrapVideo(String mediaId, String title, String description) {
        
        return PassiveMessage.wrapVideo(FromUserName, ToUserName, mediaId, title, description);
    }
    
    /**
     * @param MusicUrl 音乐链接
     * @param ThumbMediaId 缩略图的媒体id，通过上传多媒体文件，得到的id
     * @return
     */
    public String wrapMusic(String musicUrl, String thumbMediaId) {
        
        return PassiveMessage.wrapMusic(FromUserName, ToUserName, musicUrl, thumbMediaId);
    }

    /**
     * @param Title 音乐标题
     * @param Description 音乐描述
     * @param MusicUrl 音乐链接
     * @param HQMusicUrl 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     * @param ThumbMediaId 缩略图的媒体id，通过上传多媒体文件，得到的id
     * @return
     */
    public String wrapMusic(String title, String description, String musicUrl, String hqMusicUrl, String thumbMediaId) {
        
        return PassiveMessage.wrapMusic(FromUserName, ToUserName, title, description, musicUrl, hqMusicUrl, thumbMediaId);
    }
    
    /**
     * @param articles 新闻列表
     * @return
     */
    public String wrapNews(List<NewsArticle> articles) {
        
        return PassiveMessage.wrapNews(FromUserName, ToUserName, articles);
    }
    
}
