package com.bj58.hrg.investment.wx.listener.impl.service.end.message;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.annotation.Param;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.Service;
import com.bj58.hrg.investment.wx.listener.impl.service.route.RawMessageRouter;

/**
 * @class LocationMessageAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=RawMessageRouter.class, value="location")
public class LocationMessageAdaptor extends AbstractMessage implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String Location_X;   //地理位置维度
    @Param protected String Location_Y;   //地理位置经度
    @Param protected String Scale;        //地图缩放大小
    @Param protected String Label;        //地理位置信息

    @Override
    public String doService(Context context) throws Exception {

        log.info(String.format("收到来自 %s 的位置信息 %s-%s", FromUserName, 
                                Location_X, Location_Y));
        return DEFAULT_RETURN;
    }
    
    
    
}
