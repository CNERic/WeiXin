package cn.lzxz1234.weixin.api.wx.listener.service.end.message;

import cn.lzxz1234.weixin.api.wx.dto.Context;
import cn.lzxz1234.weixin.api.wx.listener.Service;
import cn.lzxz1234.weixin.api.wx.vo.message.VoiceMessage;
import org.apache.log4j.Logger;

/**
 * @class VoiceMessageService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class VoiceMessageService extends AbstractMessageService implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Override
    public String doService(Context context) throws Exception {

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
