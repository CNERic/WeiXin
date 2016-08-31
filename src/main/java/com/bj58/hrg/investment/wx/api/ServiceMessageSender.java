package com.bj58.hrg.investment.wx.api;

import static com.bj58.hrg.investment.common.StringTemplate.compile;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.bj58.hrg.investment.common.HttpUtils;
import com.bj58.hrg.investment.common.StringTemplate;
import com.bj58.hrg.investment.wx.annotation.Autowired;
import com.bj58.hrg.investment.wx.annotation.Service;
import com.bj58.hrg.investment.wx.vo.request.ServiceMusicRequest;
import com.bj58.hrg.investment.wx.vo.request.ServiceTextRequest;
import com.bj58.hrg.investment.wx.vo.request.ServiceVideoRequest;
import com.bj58.hrg.investment.wx.vo.request.ServiceVoiceRequest;

/**
 * @class ServiceMessageSender
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Service
public class ServiceMessageSender {

    private static Logger log = Logger.getLogger(ServiceMessageSender.class);
    private static final StringTemplate sendServiceUrl = compile(WeiXinURL.SEND_SERVICE);
    @Autowired
    private TokenAccessor tokenAccessor;
    
    /**
     * @param touser 普通用户openid
     * @param content 文本消息内容
    */
    public void sendText(String touser, String content) {
        
        send(JSON.toJSONString(new ServiceTextRequest(touser, content)));
        log.info(String.format("向用户[%s]发送信息[%s]", touser, content));
    }
    
    /**
     * @param touser 普通用户openid
     * @param media_id 发送的语音的媒体ID
    */
    public void sendVoice(String touser, String media_id) {
        
        send(JSON.toJSONString(new ServiceVoiceRequest(touser, media_id)));
    }
    
    /**
     * @param touser 普通用户openid
     * @param media_id 发送的视频的媒体ID
     * @param thumb_media_id 缩略图的媒体ID
     * @param title 视频消息的标题
     * @param description 视频消息的描述
    */
    public void sendVideo(String touser, String media_id, 
            String thumb_media_id, String title, String description) {
        
        send(JSON.toJSONString(new ServiceVideoRequest(touser, media_id, thumb_media_id, title, description)));
    }
    
    /**
     * @param touser 普通用户openid
     * @param title 音乐标题
     * @param description 音乐描述
     * @param musicurl 音乐链接 
     * @param hqmusicurl 高品质音乐链接，wifi环境优先使用该链接播放音乐
     * @param thumb_media_id 缩略图的媒体ID
    */
    public void sendMusic(String touser, String title, String description,
            String musicurl, String hqmusicurl, String thumb_media_id) {
        
        send(JSON.toJSONString(new ServiceMusicRequest(null, title, description, musicurl, hqmusicurl, thumb_media_id)));
    }
    
    private String send(String xml) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        String urlLocation = sendServiceUrl.replace(params);
        return  HttpUtils.post(urlLocation, xml);
    }
    
}
