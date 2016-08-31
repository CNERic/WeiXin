package com.bj58.hrg.investment.wx.listener.impl.service.end.event;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.annotation.Param;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.Service;
import com.bj58.hrg.investment.wx.listener.impl.service.Responsable;
import com.bj58.hrg.investment.wx.listener.impl.service.route.EventRouter;

/**
 * @class ScanEventAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=EventRouter.class, value="SCAN")
public class ScanEventAdaptor extends Responsable implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String EventKey;//事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
    @Param protected String Ticket;//二维码的ticket，可用来换取二维码图片
    
    @Override
    public String doService(Context context) throws Exception {
        
        log.info(String.format("收到来自 %s 的二维码事件 %s-%s", FromUserName, 
                                EventKey, Ticket));
        return DEFAULT_RETURN;
    }

}
