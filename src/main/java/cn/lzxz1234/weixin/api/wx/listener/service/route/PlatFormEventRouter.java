package cn.lzxz1234.weixin.api.wx.listener.service.route;

import cn.lzxz1234.weixin.api.wx.dto.Context;
import cn.lzxz1234.weixin.api.wx.listener.Service;
import org.apache.log4j.Logger;

public final class PlatFormEventRouter extends AbstractRouter implements Service {

    private Logger log = Logger.getLogger(PlatFormEventRouter.class);

    private PlatFormEventListener[] listeners = new PlatFormEventListener[0];

    @Override
    public String doService(Context context) throws Exception {

        String infoType = context.getString("InfoType");
        for(PlatFormEventListener listener : listeners) {
            if(infoType.equals("component_verify_ticket"))
                listener.handleVerifyTicket(context.getString("ComponentVerifyTicket"));
            else if(infoType.equals("unauthorized"))
                listener.handleUnAuthorized(context.getString("AuthorizerAppid"));
            else
                log.warn("不支持 " + infoType + " 类型的平台事件处理");
        }
        return DEFAULT_RETURN;
    }

    public interface PlatFormEventListener {

        void handleVerifyTicket(String verifyTicket);

        void handleUnAuthorized(String authorizerAppid);
    }

}
