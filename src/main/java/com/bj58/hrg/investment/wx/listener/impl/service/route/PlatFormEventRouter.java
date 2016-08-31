package com.bj58.hrg.investment.wx.listener.impl.service.route;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.annotation.Param;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.Service;
import com.bj58.hrg.investment.wx.listener.ThreadsMode;

@Node(value = "platform", parents = {RawMessageRouter.class})
public final class PlatFormEventRouter implements Service {

    protected Logger log = Logger.getLogger(PlatFormEventRouter.class);
    
    @Param private ThreadsMode threadsMode;
    @Param private String InfoType;
    
    @Override
    public String doService(Context context) throws Exception {

        log.info(String.format("第三方平台服务根据类型 [%s] 作路由", InfoType));
        return threadsMode.routeToNext(this.getClass(), InfoType, context);
    }

}
