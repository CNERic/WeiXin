package cn.lzxz1234.weixin.api.wx.listener.service.route;

import cn.lzxz1234.tencent.weixin.wx.annotation.Param;
import cn.lzxz1234.weixin.api.wx.dto.Context;
import cn.lzxz1234.weixin.api.wx.listener.Service;
import cn.lzxz1234.weixin.api.wx.listener.service.end.message.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;

/**
 * @class MessageService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public final class RawMessageRouter extends AbstractRouter implements Service {

    @Param
    private String xmlContent;
    {
        this.put("platform", new PlatFormEventRouter());
        this.put("event", new EventRouter());

        this.put("text", new TextMessageService());
        this.put("image", new ImageMessageService());
        this.put("link", new LinkMessageService());
        this.put("location", new LocationMessageService());
        this.put("shortvideo", new ShortVideoMessageService());
        this.put("video", new VideoMessageService());
        this.put("voice", new VoiceMessageService());
    }
    @Override
    public String doService(Context context) throws Exception {
        
        Document document = DocumentHelper.parseText(xmlContent);
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
            return this.doNext(routeKey, context);
        }
    }

}
