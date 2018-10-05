package cn.lzxz1234.weixin.api.wx.listener.service.end.message;

import cn.lzxz1234.weixin.api.wx.dto.Context;
import cn.lzxz1234.weixin.api.wx.listener.Service;
import cn.lzxz1234.weixin.api.wx.vo.message.ShortVideoMessage;

/**
 * @class ShortVideoMessageService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class ShortVideoMessageService extends AbstractMessageService implements Service {

    @Override
    public String doService(Context context) throws Exception {

        ShortVideoMessage message = new ShortVideoMessage();
        message.setCreateTime(context.getString("CreateTime"));
        message.setFromUserName(context.getString("FromUserName"));
        message.setMsgId(context.getString("MsgId"));
        message.setMsgType(context.getString("MsgType"));
        message.setToUserName(context.getString("ToUserName"));

        message.setMediaId(context.getString("MediaId"));
        message.setThumbMediaId(context.getString("ThumbMediaId"));
        return DEFAULT_RETURN;
    }

}
