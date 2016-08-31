package com.bj58.hrg.investment.wx.listener.impl.service.end.event;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.annotation.Param;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.Service;
import com.bj58.hrg.investment.wx.listener.impl.service.Responsable;
import com.bj58.hrg.investment.wx.listener.impl.service.route.EventRouter;

/**
 * @class ClickEventAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=EventRouter.class, value="CLICK")
public class ClickEventAdaptor extends Responsable implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String EventKey; //事件KEY值，与自定义菜单接口中KEY值对应
    
    @Override
    public String doService(Context context) throws Exception {
        
        log.info(String.format("收到来自 %s 的点击事件 %s", FromUserName, EventKey));
        return DEFAULT_RETURN;
    }

}
