package cn.lzxz1234.weixin.api.wx.listener.service.route;

import cn.lzxz1234.weixin.api.wx.dto.Context;
import cn.lzxz1234.weixin.api.wx.listener.Service;
import cn.lzxz1234.weixin.api.wx.listener.service.end.MessageListener;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @class MessageService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public final class RawMessageRouter extends AbstractRouter implements Service {

    private static MessageListener[] listeners = new MessageListener[0];

    public static void registEventListener(MessageListener listener) {

        List<MessageListener> tmpListeners = new ArrayList<>();
        tmpListeners.addAll(Arrays.asList(listeners));
        tmpListeners.add(listener);
        listeners = tmpListeners.toArray(new MessageListener[0]);
    }

    {
        this.put("platform", new PlatFormEventRouter());
        this.put("event", new EventRouter());
    }
    @Override
    public String doService(Context context) throws Exception {
        
        Document document = DocumentHelper.parseText(context.getString("xmlContent"));
        Element root = document.getRootElement();
        for(Iterator<?> it = root.elementIterator(); it.hasNext();) {
            Element ele = (Element)it.next();
            context.addAttribute(ele.getName(), ele.getText());
        }
        
        String appId = context.getAttribute(String.class, "AppId");
        if(appId != null) { //此时为第三方服务平台服务自身的消息
            return this.doNext("platform", context);
        } else {
            String routeKey = context.getAttribute(String.class, "MsgType");
            for(MessageListener listener : listeners) {
                try {
                    if (routeKey.equals("text")) {
                        listener.handleTextMessage(context.getString("FromUserName"), context.getString("ToUserName"),
                                context.getString("Content"));
                    } else if(routeKey.equals("image")) {
                        listener.handleImageMessage(context.getString("FromUserName"), context.getString("ToUserName"),
                                context.getString("MediaId"), context.getString("PicUrl"), context.getString("picLocation"));
                    } else if(routeKey.equals("link")) {
                        listener.handleLinkMessage(context.getString("FromUserName"), context.getString("ToUserName"),
                                context.getString("Title"), context.getString("Description"), context.getString("Url"));
                    } else if(routeKey.equals("location")) {
                        listener.handleLocationMessage(context.getString("FromUserName"), context.getString("ToUserName"),
                                context.getString("Location_X"), context.getString("Location_Y"), context.getString("Scale"), context.getString("Label"));
                    } else if(routeKey.equals("shortvideo")) {
                        listener.handleShortVideo(context.getString("FromUserName"), context.getString("ToUserName"),
                                context.getString("MediaId"), context.getString("ThumbMediaId"));
                    } else if(routeKey.equals("video")) {
                        listener.handleVideo(context.getString("FromUserName"), context.getString("ToUserName"),
                                context.getString("MediaId"), context.getString("ThumbMediaId"));
                    } else if(routeKey.equals("voice")) {
                        listener.handleVoice(context.getString("FromUserName"), context.getString("ToUserName"),
                                context.getString("MediaId"), context.getString("Format"), context.getString("voiceLocation"));
                    } else {
                        log.warn("不支持的消息类型: " + routeKey);
                    }
                } catch (Exception e) {
                    log.error("通知失败", e);
                }
            }
            return DEFAULT_RETURN;
        }
    }

}
