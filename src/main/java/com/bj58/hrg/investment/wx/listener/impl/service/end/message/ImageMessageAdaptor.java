package com.bj58.hrg.investment.wx.listener.impl.service.end.message;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.annotation.Param;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.Service;
import com.bj58.hrg.investment.wx.listener.impl.service.route.RawMessageRouter;

/**
 * @class ImageMessageAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=RawMessageRouter.class, value="image")
public class ImageMessageAdaptor extends AbstractMessage implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String PicUrl;       //图片链接
    @Param protected String MediaId;      //图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
    
    @Override
    public String doService(Context context) throws Exception {

        log.info(String.format("收到来自 %s 的图片信息 %s", FromUserName, MediaId));
        return DEFAULT_RETURN;
    }

}
