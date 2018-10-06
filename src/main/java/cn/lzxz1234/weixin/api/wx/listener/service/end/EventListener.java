package cn.lzxz1234.weixin.api.wx.listener.service.end;

/**
 * @author lzxz1234
 * @version v1.0
 * @class EventListener
 */
public interface EventListener {

    /**
     * @param event 事件KEY值，与自定义菜单接口中KEY值对应
     */
    String handleClick(String from, String to, String event);

    /**
     * @param latitude 地理位置纬度
     * @param lngtitude 地理位置经度
     * @param precision 地理位置精度
     */
    String handleLocation(String from, String to, String latitude, String lngtitude, String precision);

    /**
     * @param key 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
     * @param ticket 二维码的ticket，可用来换取二维码图片
     */
    String handleScan(String from, String to, String key, String ticket);

    /**
     * @param key 事件KEY值，qrscene_为前缀，后面为二维码的参数值
     * @param ticket 二维码的ticket，可用来换取二维码图片
     */
    String handleSubscribe(String from, String to, String key, String ticket);

    String handleUnSubscribe(String from, String to);

    /**
     * @param key 事件KEY值，设置的跳转URL
     */
    String handleView(String from, String to, String key);

}
