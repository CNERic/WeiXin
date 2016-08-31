package com.bj58.hrg.investment.wx.template;

import static com.bj58.hrg.investment.common.StringTemplate.compileFromClassPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bj58.hrg.investment.common.StringTemplate;
import com.bj58.hrg.investment.wx.vo.NewsArticle;

/**
 * @class PassiveResp
 * @author lzxz1234
 * @description 被动回复
 * @version v1.0
 */
public class PassiveMessage {

    private static StringTemplate T_TEXT          = compileFromClassPath("/com/bj58/hrg/investment/wx/template/tpl/passive-text.xml");
    private static StringTemplate T_IMAGE         = compileFromClassPath("/com/bj58/hrg/investment/wx/template/tpl/passive-image.xml");
    private static StringTemplate T_VOICE         = compileFromClassPath("/com/bj58/hrg/investment/wx/template/tpl/passive-voice.xml");
    private static StringTemplate T_VIDEO         = compileFromClassPath("/com/bj58/hrg/investment/wx/template/tpl/passive-video.xml");
    private static StringTemplate T_MUSIC         = compileFromClassPath("/com/bj58/hrg/investment/wx/template/tpl/passive-music.xml");
    private static StringTemplate T_NEWS          = compileFromClassPath("/com/bj58/hrg/investment/wx/template/tpl/passive-news.xml");
    private static StringTemplate T_NEWS_Articles = compileFromClassPath("/com/bj58/hrg/investment/wx/template/tpl/passive-news-article.xml");

    /**
     * @param ToUserName 接收方帐号（收到的OpenID）
     * @param FromUserName 开发者微信号   
     * @param Content
    */
    public static String wrapText(String ToUserName, String FromUserName, String Content) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ToUserName", ToUserName);
        params.put("FromUserName", FromUserName);
        params.put("CreateTime", Long.toString(System.currentTimeMillis()));
        params.put("Content", Content);
        return T_TEXT.replace(params);
    }
    
    /**
     * @param ToUserName 接收方帐号（收到的OpenID）
     * @param FromUserName 开发者微信号   
     * @param MediaId 通过上传多媒体文件，得到的id
     */
    public static String wrapImage(String ToUserName, String FromUserName, String MediaId) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ToUserName", ToUserName);
        params.put("FromUserName", FromUserName);
        params.put("CreateTime", Long.toString(System.currentTimeMillis()));
        params.put("MediaId", MediaId);
        return T_IMAGE.replace(params);
    }
    
    /**
     * @param ToUserName 接收方帐号（收到的OpenID）
     * @param FromUserName 开发者微信号   
     * @param MediaId 通过上传多媒体文件，得到的id
     */
    public static String wrapVoice(String ToUserName, String FromUserName, String MediaId) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ToUserName", ToUserName);
        params.put("FromUserName", FromUserName);
        params.put("CreateTime", Long.toString(System.currentTimeMillis()));
        params.put("MediaId", MediaId);
        return T_VOICE.replace(params);
    }
    
    /**
     * @param ToUserName 接收方帐号（收到的OpenID）
     * @param FromUserName 开发者微信号   
     * @param MediaId 通过上传多媒体文件，得到的id
     */
    public static String wrapVideo(String ToUserName, String FromUserName, String MediaId) {
        
        return wrapVideo(ToUserName, FromUserName, MediaId, "", "");
    }
    
    /**
     * @param ToUserName 接收方帐号（收到的OpenID）
     * @param FromUserName 开发者微信号   
     * @param MediaId 通过上传多媒体文件，得到的id
     * @param Title 视频消息的标题
     * @param Description 视频消息的描述
     */
    public static String wrapVideo(String ToUserName, String FromUserName, 
            String MediaId, String Title, String Description) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ToUserName", ToUserName);
        params.put("FromUserName", FromUserName);
        params.put("CreateTime", Long.toString(System.currentTimeMillis()));
        params.put("MediaId", MediaId);
        params.put("Title", Title);
        params.put("Description", Description);
        return T_VIDEO.replace(params);
    }
    
    /**
     * @param ToUserName 接收方帐号（收到的OpenID）
     * @param FromUserName 开发者微信号   
     * @param MusicUrl 音乐链接
     * @param ThumbMediaId 缩略图的媒体id，通过上传多媒体文件，得到的id
     */
    public static String wrapMusic(String ToUserName, String FromUserName, 
            String MusicUrl, String ThumbMediaId) {
        
        return wrapMusic(ToUserName, FromUserName, "", "", MusicUrl, MusicUrl, ThumbMediaId);
    }
    
    /**
     * @param ToUserName 接收方帐号（收到的OpenID）
     * @param FromUserName 开发者微信号   
     * @param Title 音乐标题
     * @param Description 音乐描述
     * @param MusicUrl 音乐链接
     * @param HQMusicUrl 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     * @param ThumbMediaId 缩略图的媒体id，通过上传多媒体文件，得到的id
     */
    public static String wrapMusic(String ToUserName, String FromUserName, 
            String Title, String Description, String MusicUrl, String HQMusicUrl,
            String ThumbMediaId) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ToUserName", ToUserName);
        params.put("FromUserName", FromUserName);
        params.put("CreateTime", Long.toString(System.currentTimeMillis()));
        params.put("Title", Title);
        params.put("Description", Description);
        params.put("MusicUrl", MusicUrl);
        params.put("HQMusicUrl", HQMusicUrl);
        params.put("ThumbMediaId", ThumbMediaId);
        return T_MUSIC.replace(params);
    }
    
    /**
     * @param ToUserName 接收方帐号（收到的OpenID）
     * @param FromUserName 开发者微信号
     * @param articles 新闻列表
     */
    public static String wrapNews(String ToUserName, String FromUserName, List<NewsArticle> articles) {
        
        StringBuilder articleString = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        for(NewsArticle article : articles) {
            params.put("Title", article.getTitle());
            params.put("Description", article.getDescription());
            params.put("PicUrl", article.getPicurl());
            params.put("Url", article.getUrl());
            articleString.append(T_NEWS_Articles.replace(params));
            params.clear();
        }
        
        params.put("ToUserName", ToUserName);
        params.put("FromUserName", FromUserName);
        params.put("CreateTime", Long.toString(System.currentTimeMillis()));
        params.put("ArticleCount", articles.size());
        params.put("Articles", articleString.toString());
        return T_NEWS.replace(params);
    }
}
