package com.bj58.hrg.investment.wx.listener.impl.service.end.event;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.Service;
import com.bj58.hrg.investment.wx.listener.impl.service.Responsable;
import com.bj58.hrg.investment.wx.listener.impl.service.route.EventRouter;

/**
 * @class UnSubscribeEventAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=EventRouter.class, value="unsubscribe")
public class UnSubscribeEventAdaptor extends Responsable implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Override
    public String doService(Context context) throws Exception {

        log.info(String.format("收到 %s 的退订事件", FromUserName));
        return DEFAULT_RETURN;
    }

}
