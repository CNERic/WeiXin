package cn.lzxz1234.weixin.api.wx.listener.service.route;

import cn.lzxz1234.weixin.api.wx.dto.Context;
import cn.lzxz1234.weixin.api.wx.listener.Service;
import org.apache.log4j.Logger;

/**
 * @class EventRouter
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public final class EventRouter extends AbstractRouter implements Service {

    private static Logger log = Logger.getLogger(EventRouter.class);
    private EventListener[] eventListeners = new EventListener[0];

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

    public interface EventListener {

        /**
         * @param event 事件KEY值，与自定义菜单接口中KEY值对应
         */
        void handleClick(String from, String to, String event);

        /**
         * @param latitude 地理位置纬度
         * @param lngtitude 地理位置经度
         * @param precision 地理位置精度
         */
        void handleLocation(String from, String to, String latitude, String lngtitude, String precision);

        /**
         * @param key 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
         * @param ticket 二维码的ticket，可用来换取二维码图片
         */
        void handleScan(String from, String to, String key, String ticket);

        /**
         * @param key 事件KEY值，qrscene_为前缀，后面为二维码的参数值
         * @param ticket 二维码的ticket，可用来换取二维码图片
         */
        void handleSubscribe(String from, String to, String key, String ticket);

        void handleUnSubscribe(String from, String to);

        /**
         * @param key 事件KEY值，设置的跳转URL
         */
        void handleView(String from, String to, String key);
    }

}
