package com.bj58.hrg.investment.wx.listener.impl.service.end.event;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.annotation.Param;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.Service;
import com.bj58.hrg.investment.wx.listener.impl.service.Responsable;
import com.bj58.hrg.investment.wx.listener.impl.service.route.EventRouter;

/**
 * @class SubscribeEventAdaptor
 * @author lzxz1234
 * @description 订阅事件可能有多个来源，非扫描二维码来的订阅时相关字段不可用
 * @version v1.0
 */
@Node(parents=EventRouter.class, value="subscribe")
public class SubscribeEventAdaptor extends Responsable implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String EventKey;//事件KEY值，qrscene_为前缀，后面为二维码的参数值
    @Param protected String Ticket;//二维码的ticket，可用来换取二维码图片
    
    @Override
    public String doService(Context context) throws Exception {

        log.info(String.format("收到 %s 的订阅事件", FromUserName));
        return DEFAULT_RETURN;
    }

}
