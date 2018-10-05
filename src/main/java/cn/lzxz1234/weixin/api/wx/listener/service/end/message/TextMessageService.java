package cn.lzxz1234.weixin.api.wx.listener.service.end.message;

import cn.lzxz1234.tencent.weixin.wx.annotation.Param;
import cn.lzxz1234.weixin.api.wx.dto.Context;
import cn.lzxz1234.weixin.api.wx.listener.Service;
import cn.lzxz1234.weixin.api.wx.vo.message.TextMessage;
import org.apache.log4j.Logger;

/**
 * @class TextMessageService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class TextMessageService extends AbstractMessageService implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Override
    public String doService(Context context) throws Exception {
        
        TextMessage textMessage = new TextMessage();
        textMessage.setContent(context.getString("Content"));
        textMessage.setCreateTime(context.getString("CreateTime"));
        textMessage.setFromUserName(context.getString("FromUserName"));
        textMessage.setMsgId(context.getString("MsgId"));
        textMessage.setMsgType(context.getString("MsgType"));
        textMessage.setToUserName(context.getString("ToUserName"));
        return DEFAULT_RETURN;
    }

}
