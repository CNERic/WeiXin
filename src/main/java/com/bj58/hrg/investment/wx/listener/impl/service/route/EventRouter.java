package com.bj58.hrg.investment.wx.listener.impl.service.route;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.annotation.Param;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.Service;
import com.bj58.hrg.investment.wx.listener.ThreadsMode;

/**
 * @class EventRouter
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=RawMessageRouter.class, value="event")
public final class EventRouter implements Service {

    private Logger log = Logger.getLogger(EventRouter.class);
    @Param private String Event;
    @Param private ThreadsMode threadsMode;

    @Override
    public String doService(Context context) throws Exception {

        log.info(String.format("根据事件[%s]做路由", Event));
        return threadsMode.routeToNext(this.getClass(), this.Event, context);
    }
    
}
