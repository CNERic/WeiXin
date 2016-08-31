package com.bj58.hrg.investment.wx.listener.impl.service.end.event;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.bj58.hrg.investment.common.StringTemplate;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.impl.service.end.ServiceTest;

public class SubscribeEventAdaptorTest extends ServiceTest {

    StringTemplate template = StringTemplate.compile(
            "<xml><ToUserName><![CDATA[${toUser}]]></ToUserName>  " + 
            "<FromUserName><![CDATA[${fromUser}]]></FromUserName> " + 
            "<CreateTime>${createtime}</CreateTime>                " + 
            "<MsgType><![CDATA[event]]></MsgType>              " + 
            "<Event><![CDATA[subscribe]]></Event>              " + 
            "<EventKey><![CDATA[${eventkey}]]></EventKey>   " + 
            "<Ticket><![CDATA[${ticket}]]></Ticket>               " + 
            "</xml>");
    String eventKey = randomString();
    String ticket = randomString();
    
    @Test
    public void test() throws Exception {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("toUser", toUserName);
        params.put("fromUser", fromUserName);
        params.put("createtime", createTime);
        params.put("eventkey", eventKey);
        params.put("ticket", ticket);
        MockSubscribeEventService service = this.preparToTest(MockSubscribeEventService.class);
        Context context = this.doPostCtxt(template.replace(params));
        
        Mockito.when(service.doService(context)).thenReturn(expectReturn);
        
        String realReturn = handler.process(context);
        Assert.assertEquals(expectReturn, realReturn);
        Assert.assertEquals(eventKey, service.EventKey);
        Assert.assertEquals(ticket, service.Ticket);
        Mockito.verify(service).doService(context);
    }

    public static class MockSubscribeEventService extends SubscribeEventAdaptor {
        
    }
}
