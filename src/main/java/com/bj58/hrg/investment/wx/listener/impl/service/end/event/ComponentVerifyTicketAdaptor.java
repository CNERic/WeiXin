package com.bj58.hrg.investment.wx.listener.impl.service.end.event;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.wx.annotation.Autowired;
import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.annotation.Param;
import com.bj58.hrg.investment.wx.api.PlatFormTokenAccessor;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.Service;
import com.bj58.hrg.investment.wx.listener.impl.service.route.EventRouter;
import com.bj58.hrg.investment.wx.listener.impl.service.route.PlatFormEventRouter;

@Node(parents={PlatFormEventRouter.class, EventRouter.class}, value="component_verify_ticket")
public class ComponentVerifyTicketAdaptor implements Service {

    protected Logger log = Logger.getLogger(ComponentVerifyTicketAdaptor.class);
    
    @Param protected String AppId;
    @Param protected String CreateTime;
    @Param protected String ComponentVerifyTicket;
    @Autowired private PlatFormTokenAccessor platFormTokenAccessor;
    
    @Override
    public String doService(Context context) throws Exception {
        
        platFormTokenAccessor.updatePlatFormVerifyTicket(ComponentVerifyTicket);
        log.info(String.format("收到刷新授权Ticket请求，新Ticket: %s", ComponentVerifyTicket));
        return DEFAULT_RETURN;
    }

}
