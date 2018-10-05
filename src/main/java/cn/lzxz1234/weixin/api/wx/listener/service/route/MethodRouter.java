package cn.lzxz1234.weixin.api.wx.listener.service.route;

import cn.lzxz1234.weixin.api.wx.dto.Context;
import cn.lzxz1234.weixin.api.wx.listener.Service;
import cn.lzxz1234.weixin.api.wx.listener.service.end.CertifyService;
import org.apache.log4j.Logger;

/**
 * @class MethodRouter
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public final class MethodRouter extends AbstractRouter implements Service {

    private Logger log = Logger.getLogger(MethodRouter.class);
    
    {
        this.put("GET", new CertifyService());
        this.put("POST", new EncryptRouter());
    }
    
    @Override
    public String doService(Context context) throws Exception {

        return this.doNext((String) context.getAttribute("method"), context);
    }

}
