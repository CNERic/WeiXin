package cn.lzxz1234.weixin.api.wx.listener.service.end;

/**
 * @author lzxz1234
 * @version v1.0
 * @class MessageListener
 */
public interface MessageListener {

    void handleTextMessage(String fromUserName, String toUserName, String content);

    void handleImageMessage(String fromUserName, String toUserName, String mediaId, String picUrl, String picLocation);

    void handleLinkMessage(String fromUserName, String toUserName, String title, String description, String url);

    void handleLocationMessage(String fromUserName, String toUserName, String location_x, String location_y, String scale, String label);

    void handleShortVideo(String fromUserName, String toUserName, String mediaId, String thumbMediaId);

    void handleVideo(String fromUserName, String toUserName, String mediaId, String thumbMediaId);

    void handleVoice(String fromUserName, String toUserName, String mediaId, String format, String voiceLocation);
}
