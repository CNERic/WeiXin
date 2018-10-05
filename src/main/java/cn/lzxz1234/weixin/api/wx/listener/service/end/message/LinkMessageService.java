package cn.lzxz1234.weixin.api.wx.listener.service.end.message;

import cn.lzxz1234.tencent.weixin.wx.annotation.Param;
import cn.lzxz1234.weixin.api.wx.dto.Context;
import cn.lzxz1234.weixin.api.wx.listener.Service;
import cn.lzxz1234.weixin.api.wx.vo.message.LinkMessage;

import cn.lzxz1234.tencent.weixin.wx.annotation.Node;

/**
 * @class LinkMessageService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class LinkMessageService extends AbstractMessageService implements Service {

    @Override
    public String doService(Context context) throws Exception {

        LinkMessage message = new LinkMessage();
        message.setCreateTime(context.getString("CreateTime"));
        message.setFromUserName(context.getString("FromUserName"));
        message.setMsgId(context.getString("MsgId"));
        message.setMsgType(context.getString("MsgType"));
        message.setToUserName(context.getString("ToUserName"));

        message.setTitle(context.getString("Title"));
        message.setDescription(context.getString("Description"));
        message.setUrl(context.getString("Url"));
        return DEFAULT_RETURN;
    }

}
