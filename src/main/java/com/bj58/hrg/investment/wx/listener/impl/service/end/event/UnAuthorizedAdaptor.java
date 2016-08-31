package com.bj58.hrg.investment.wx.listener.impl.service.end.event;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.annotation.Param;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.Service;
import com.bj58.hrg.investment.wx.listener.impl.service.route.EventRouter;
import com.bj58.hrg.investment.wx.listener.impl.service.route.PlatFormEventRouter;

@Node(parents={PlatFormEventRouter.class, EventRouter.class}, value="unauthorized")
public class UnAuthorizedAdaptor implements Service {

    protected Logger log = Logger.getLogger(UnAuthorizedAdaptor.class);
    
    @Param protected String AppId;
    @Param protected String CreateTime;
    @Param protected String AuthorizerAppid;
    
    @Override
    public String doService(Context context) throws Exception {
        
        log.info(String.format("收到取消授权事件，公众号ID： %s", AuthorizerAppid));
        return DEFAULT_RETURN;
    }

}
