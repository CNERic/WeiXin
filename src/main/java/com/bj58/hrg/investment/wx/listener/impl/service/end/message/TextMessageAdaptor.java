package com.bj58.hrg.investment.wx.listener.impl.service.end.message;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.annotation.Param;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.Service;
import com.bj58.hrg.investment.wx.listener.impl.service.route.RawMessageRouter;

/**
 * @class TextMessageAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=RawMessageRouter.class, value="text")
public class TextMessageAdaptor extends AbstractMessage implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String Content;      //消息id，64位整型
    
    @Override
    public String doService(Context context) throws Exception {
        
        log.info(String.format("收到来自 %s 的文本信息 %s", FromUserName, Content));
        return DEFAULT_RETURN;
    }

}
