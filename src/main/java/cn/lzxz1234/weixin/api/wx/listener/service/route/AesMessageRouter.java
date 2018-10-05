package cn.lzxz1234.weixin.api.wx.listener.service.route;

import cn.lzxz1234.weixin.api.common.aes.WXBizMsgCrypt;
import cn.lzxz1234.weixin.api.wx.dto.App;
import cn.lzxz1234.weixin.api.wx.dto.Context;
import cn.lzxz1234.weixin.api.wx.listener.Service;

/**
 * @class AESService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public final class AesMessageRouter extends AbstractRouter implements Service {

    private WXBizMsgCrypt msgCrypt = new WXBizMsgCrypt(App.Info.token, App.Info.aesKey, App.Info.id);

    {
        this.put("raw", new RawMessageRouter());
    }
    @Override
    public String doService(Context context) throws Exception {

        String xmlContent = context.getString("xmlContent");
        //解密，并将解密后信息放入上下文
        xmlContent = msgCrypt.decryptMsg(
                context.getString("msg_signature"),
                context.getString("timestamp"),
                context.getString("nonce"),
                xmlContent);
        context.setAttribute("xmlContent", xmlContent);
        
        //调用下一环节
        String result = this.doNext("raw", context);
        
        //加密并组 XML 返回
        return msgCrypt.encryptMsg(result, System.currentTimeMillis() + "", context.getString("nonce"));
    }
    
}
