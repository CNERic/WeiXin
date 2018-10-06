package cn.lzxz1234.weixin.api.wx;

import cn.lzxz1234.weixin.api.common.HttpUtils;
import cn.lzxz1234.weixin.api.common.IOUtils;
import cn.lzxz1234.weixin.api.common.StringUtils;
import cn.lzxz1234.weixin.api.wx.dto.Context;
import cn.lzxz1234.weixin.api.wx.listener.service.end.EventListener;
import cn.lzxz1234.weixin.api.wx.listener.service.end.MessageListener;
import cn.lzxz1234.weixin.api.wx.listener.service.end.message.AbstractMessageService;
import cn.lzxz1234.weixin.api.wx.listener.service.route.EventRouter;
import cn.lzxz1234.weixin.api.wx.listener.service.route.MethodRouter;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @class WeiXinHandler
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class WeiXinHandler {

    private Logger log = Logger.getLogger(WeiXinHandler.class);
    private MethodRouter router = new MethodRouter();

    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        Context context = new Context(HttpUtils.decodeParams(req));
        context.setAttribute("method", req.getMethod());
        if(req.getMethod().equalsIgnoreCase("POST"))
            context.setAttribute("xmlContent", HttpUtils.read(req));
        
        OutputStream os = resp.getOutputStream();
        try {
            String responseString = router.doService(context);
            os.write(StringUtils.getBytesUtf8(responseString));
        } catch (Exception e) {
            log.error("消息处理失败", e);
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

    public void registEventListener(EventListener listener) {

        EventRouter.registEventListener(listener);
    }

    public void registMsgListener(MessageListener listener) {

        AbstractMessageService.registEventListener(listener);
    }

}
