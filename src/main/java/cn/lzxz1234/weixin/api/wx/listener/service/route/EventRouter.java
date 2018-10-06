package cn.lzxz1234.weixin.api.wx.listener.service.route;

import cn.lzxz1234.weixin.api.wx.dto.Context;
import cn.lzxz1234.weixin.api.wx.listener.Service;
import cn.lzxz1234.weixin.api.wx.listener.service.end.EventListener;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @class EventRouter
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public final class EventRouter extends AbstractRouter implements Service {

    private static Logger log = Logger.getLogger(EventRouter.class);
    private static EventListener[] eventListeners = new EventListener[0];

    public static void registEventListener(EventListener listener) {

        List<EventListener> newEventListeners = new ArrayList<>();
        newEventListeners.addAll(Arrays.asList(eventListeners));
        newEventListeners.add(listener);
        eventListeners = newEventListeners.toArray(new EventListener[0]);
    }

    @Override
    public String doService(Context context) throws Exception {

        String event = context.getString("Event");
        for(EventListener each : eventListeners) {
            try {
                if(event.equals("CLICK"))
                    each.handleClick(context.getString("FromUserName"), context.getString("ToUserName"), context.getString("EventKey"));
                else if(event.equals("LOCATION"))
                    each.handleLocation(context.getString("FromUserName"), context.getString("ToUserName"), context.getString("Latitude"), context.getString("Longitude"), context.getString("Precision"));
                else if(event.equals("SCAN"))
                    each.handleScan(context.getString("FromUserName"), context.getString("ToUserName"), context.getString("EventKey"), context.getString("Ticket"));
                else if(event.equals("subscribe"))
                    each.handleSubscribe(context.getString("FromUserName"), context.getString("ToUserName"), context.getString("EventKey"), context.getString("Ticket"));
                else if(event.equals("unsubscribe"))
                    each.handleUnSubscribe(context.getString("FromUserName"), context.getString("ToUserName"));
                else if(event.equals("VIEW"))
                    each.handleView(context.getString("FromUserName"), context.getString("ToUserName"), context.getString("EventKey"));
                else
                    log.warn("不支持 " + event + " 类型的事件处理");
            } catch (Exception e) {
                log.error("通知失败", e);
            }
        }
        // TODO 客服消息接口下发返回
        return DEFAULT_RETURN;
    }

}
