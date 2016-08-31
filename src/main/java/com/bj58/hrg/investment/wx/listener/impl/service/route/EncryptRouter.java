package com.bj58.hrg.investment.wx.listener.impl.service.route;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.annotation.Param;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.Service;
import com.bj58.hrg.investment.wx.listener.ThreadsMode;

/**
 * @class EncryptRouter
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(value = "POST", parents = MethodRouter.class)
public final class EncryptRouter implements Service {

    private Logger log = Logger.getLogger(EncryptRouter.class);
    
    @Param(value="encrypt_type", defaultValue="raw") 
    private String encrypt_type; //加密类型
    @Param private ThreadsMode threadsMode;
    
    @Override
    public String doService(Context context) throws Exception {
        
        log.info(String.format("根据加密类型[%s]做路由。", encrypt_type));
        return threadsMode.routeToNext(this.getClass(), this.encrypt_type, context);
    }

}
