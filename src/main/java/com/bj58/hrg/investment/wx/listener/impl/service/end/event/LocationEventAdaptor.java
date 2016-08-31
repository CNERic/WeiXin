package com.bj58.hrg.investment.wx.listener.impl.service.end.event;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.annotation.Param;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.Service;
import com.bj58.hrg.investment.wx.listener.impl.service.Responsable;
import com.bj58.hrg.investment.wx.listener.impl.service.route.EventRouter;

/**
 * @class LocationEventAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=EventRouter.class, value="LOCATION")
public class LocationEventAdaptor extends Responsable implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String Latitude;//地理位置纬度
    @Param protected String Longitude;//地理位置经度
    @Param protected String Precision;//地理位置精度
    
    @Override
    public String doService(Context context) throws Exception {

        log.info(String.format("收到来自 %s 的坐标信息 %s-%s(%s)", FromUserName, 
                                Latitude, Longitude, Precision));
        return DEFAULT_RETURN;
    }

}
