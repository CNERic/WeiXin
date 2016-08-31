package com.bj58.hrg.investment.wx.listener.impl.service.end.event;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.bj58.hrg.investment.common.StringTemplate;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.impl.service.end.ServiceTest;

public class ClickEventAdaptorTest extends ServiceTest {

    StringTemplate stp = StringTemplate.compile(
            "<xml>                                             " + 
            "<ToUserName><![CDATA[${toUser}]]></ToUserName>       " + 
            "<FromUserName><![CDATA[${fromUser}]]></FromUserName> " + 
            "<CreateTime>${createTime}</CreateTime>                " + 
            "<MsgType><![CDATA[event]]></MsgType>              " + 
            "<Event><![CDATA[CLICK]]></Event>                  " + 
            "<EventKey><![CDATA[${eventkey}]]></EventKey>         " + 
            "</xml>");
    String eventKey = randomString();
    
    @Test
    public void test() throws Exception {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("toUser", toUserName);
        params.put("fromUser", fromUserName);
        params.put("createTime", createTime);
        params.put("eventkey", eventKey);
        MockClickEventService service = this.preparToTest(MockClickEventService.class);
        Context context = this.doPostCtxt(stp.replace(params));
        Mockito.when(service.doService(context)).thenReturn(expectReturn);
        
        String realReturn = handler.process(context);
        Assert.assertEquals(expectReturn, realReturn);
        Assert.assertEquals(eventKey, service.EventKey);
        Mockito.verify(service).doService(context);
    }

    public static class MockClickEventService extends ClickEventAdaptor {
        
    }
    
}
