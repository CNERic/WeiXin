package com.bj58.hrg.investment.wx.listener.impl.service.end.message;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.annotation.Param;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.Service;
import com.bj58.hrg.investment.wx.listener.impl.service.route.RawMessageRouter;

/**
 * @class LinkMessageAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=RawMessageRouter.class, value="link")
public class LinkMessageAdaptor extends AbstractMessage implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String Title;        //消息标题
    @Param protected String Description;  //消息描述
    @Param protected String Url;          //消息链接
    
    @Override
    public String doService(Context context) throws Exception {

        log.info(String.format("收到来自 %s 的链接信息 %s", FromUserName, Title));
        return DEFAULT_RETURN;
    }

}
