package com.bj58.hrg.investment.wx.listener.impl.service.route;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.annotation.Param;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.Service;
import com.bj58.hrg.investment.wx.listener.ThreadsMode;

/**
 * @class MethodRouter
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(value = "root", parents = Service.class)
public final class MethodRouter implements Service {

    private Logger log = Logger.getLogger(MethodRouter.class);
    
    @Param("method")
    private String method;
    @Param private ThreadsMode threadsMode;
    
    @Override
    public String doService(Context context) throws Exception {
        
    	log.info(String.format("根据请求方法[%s]做路由。", method));
        return threadsMode.routeToNext(this.getClass(), this.method, context);
    }

}
