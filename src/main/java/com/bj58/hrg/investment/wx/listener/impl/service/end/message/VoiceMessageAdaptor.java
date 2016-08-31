package com.bj58.hrg.investment.wx.listener.impl.service.end.message;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.annotation.Param;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.Service;
import com.bj58.hrg.investment.wx.listener.impl.service.route.RawMessageRouter;

/**
 * @class VoiceMessageAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=RawMessageRouter.class, value="voice")
public class VoiceMessageAdaptor extends AbstractMessage implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String MediaId;      //语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
    @Param protected String Format;       //语音格式，如amr，speex等
    
    @Override
    public String doService(Context context) throws Exception {

        log.info(String.format("收到来自 %s 的音频信息 %s", FromUserName, MediaId));
        return DEFAULT_RETURN;
    }

}
