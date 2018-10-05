package cn.lzxz1234.weixin.api.wx.listener.service.end.message;

import cn.lzxz1234.tencent.weixin.wx.annotation.Param;
import cn.lzxz1234.weixin.api.wx.listener.service.route.RawMessageRouter;
import cn.lzxz1234.weixin.api.wx.vo.message.VoiceMessage;
import cn.lzxz1234.weixin.api.wx.listener.Service;
import org.apache.log4j.Logger;

import cn.lzxz1234.tencent.weixin.wx.annotation.Node;
import cn.lzxz1234.weixin.api.wx.dto.Context;

/**
 * @class VoiceMessageService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=RawMessageRouter.class, value="voice")
public class VoiceMessageService extends AbstractMessageService implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param
    protected String MediaId;      //语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
    @Param protected String Format;       //语音格式，如amr，speex等
    
    @Override
    public String doService(Context context) throws Exception {

        log.info(String.format("收到来自 %s 的音频信息 %s", FromUserName, MediaId));
        VoiceMessage message = new VoiceMessage();
        message.setCreateTime(context.getString("CreateTime"));
        message.setFromUserName(context.getString("FromUserName"));
        message.setMsgId(context.getString("MsgId"));
        message.setMsgType(context.getString("MsgType"));
        message.setToUserName(context.getString("ToUserName"));

        message.setMediaId(context.getString("MediaId"));
        message.setFormat(context.getString("Format"));
        return DEFAULT_RETURN;
    }

}
