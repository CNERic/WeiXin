package com.bj58.hrg.investment.wx;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.common.HttpUtils;
import com.bj58.hrg.investment.common.IOUtils;
import com.bj58.hrg.investment.common.StringUtils;
import com.bj58.hrg.investment.wx.dto.Context;

/**
 * @class WeiXinServlet
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class WeiXinServlet extends HttpServlet {

    private static final long serialVersionUID = 1785566409069051059L;
    
    private Logger log = Logger.getLogger(WeiXinServlet.class);
    private MessageHandler handler;
    
    @Override
    public void init() throws ServletException {
        
        try {
            handler = new MessageHandler();
        } catch (Exception e) {
            throw new ServletException("加载失败", e);
        }
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        Context context = new Context(HttpUtils.decodeParams(req));
        context.setAttribute("method", req.getMethod());
        if(req.getMethod().equalsIgnoreCase("POST"))
            context.setAttribute("xmlContent", HttpUtils.read(req));
        
        OutputStream os = resp.getOutputStream();
        try {
            String responseString = this.handler.process(context);
            os.write(StringUtils.getBytesUtf8(responseString));
        } catch (Exception e) {
            log.error("消息处理失败", e);
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

}
