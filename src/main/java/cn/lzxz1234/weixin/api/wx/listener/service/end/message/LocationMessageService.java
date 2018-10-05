package cn.lzxz1234.weixin.api.wx.listener.service.end.message;

import cn.lzxz1234.weixin.api.wx.dto.Context;
import cn.lzxz1234.weixin.api.wx.listener.Service;
import cn.lzxz1234.weixin.api.wx.vo.message.LocationMessage;

/**
 * @class LocationMessageService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class LocationMessageService extends AbstractMessageService implements Service {

    @Override
    public String doService(Context context) throws Exception {

        LocationMessage message = new LocationMessage();
        message.setCreateTime(context.getString("CreateTime"));
        message.setFromUserName(context.getString("FromUserName"));
        message.setMsgId(context.getString("MsgId"));
        message.setMsgType(context.getString("MsgType"));
        message.setToUserName(context.getString("ToUserName"));

        message.setLocation_X(context.getString("Location_X"));
        message.setLocation_Y(context.getString("Location_Y"));
        message.setScale(context.getString("Scale"));
        message.setLabel(context.getString("Label"));

        return DEFAULT_RETURN;
    }
    
    
    
}
