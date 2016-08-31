package com.bj58.hrg.investment.wx.listener.impl.service.end.event;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.bj58.hrg.investment.common.StringTemplate;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.impl.service.end.ServiceTest;

public class UnSubscribeEventAdaptorTest extends ServiceTest {

    StringTemplate template = StringTemplate.compile(
            "<xml><ToUserName><![CDATA[${toUser}]]></ToUserName>  " + 
            "<FromUserName><![CDATA[${fromUser}]]></FromUserName> " + 
            "<CreateTime>${createtime}</CreateTime>                " + 
            "<MsgType><![CDATA[event]]></MsgType>              " + 
            "<Event><![CDATA[unsubscribe]]></Event>              " + 
            "</xml>");
    
    @Test
    public void test() throws Exception {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("toUser", toUserName);
        params.put("fromUser", fromUserName);
        params.put("createtime", createTime);
        MockUnSubscribeEventService service = this.preparToTest(MockUnSubscribeEventService.class);
        Context context = this.doPostCtxt(template.replace(params));
        Mockito.when(service.doService(context)).thenReturn(expectReturn);
        
        String realReturn = handler.process(context);
        Assert.assertEquals(expectReturn, realReturn);
        Mockito.verify(service).doService(context);
    }

    public static class MockUnSubscribeEventService extends UnSubscribeEventAdaptor {
        
    }

}
