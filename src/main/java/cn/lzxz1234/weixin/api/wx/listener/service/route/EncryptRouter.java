package cn.lzxz1234.weixin.api.wx.listener.service.route;

import cn.lzxz1234.tencent.weixin.wx.annotation.Param;
import cn.lzxz1234.weixin.api.wx.dto.Context;
import cn.lzxz1234.weixin.api.wx.listener.Service;

import cn.lzxz1234.tencent.weixin.wx.annotation.Node;

/**
 * @class EncryptRouter
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public final class EncryptRouter extends AbstractRouter implements Service {

    {
        this.put("raw", new RawMessageRouter());
        this.put("aes", new AesMessageRouter());
    }
    
    @Override
    public String doService(Context context) throws Exception {
        
        return this.doNext((String)context.getAttribute("encrypt_type"), context);
    }

}
