﻿/**
 * WeiXin
 * @title EventRouter.java
 * @package com.chn.wx.listener.route
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-下午6:20:36
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.monitor.sys.route;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.monitor.Service;
import com.chn.wx.monitor.ServiceProxy;

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
    @Param private ServiceProxy serviceHolder;

    @Override
    public String doService(Context context) throws Exception {

        log.info(String.format("根据事件[%s]做路由", Event));
        return serviceHolder.routeToNext(this.Event, context);
    }
    
}