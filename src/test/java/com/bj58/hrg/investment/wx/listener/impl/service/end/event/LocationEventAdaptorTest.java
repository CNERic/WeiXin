package com.bj58.hrg.investment.wx.listener.impl.service.end.event;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.bj58.hrg.investment.common.StringTemplate;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.impl.service.end.ServiceTest;

public class LocationEventAdaptorTest extends ServiceTest {

    StringTemplate template = StringTemplate.compile(
            "<xml>                                             " + 
            "<ToUserName><![CDATA[${toUser}]]></ToUserName>       " + 
            "<FromUserName><![CDATA[${fromUser}]]></FromUserName> " + 
            "<CreateTime>${createTime}</CreateTime>                " + 
            "<MsgType><![CDATA[event]]></MsgType>              " + 
            "<Event><![CDATA[LOCATION]]></Event>               " + 
            "<Latitude>${latitude}</Latitude>                    " + 
            "<Longitude>${longitude}</Longitude>                 " + 
            "<Precision>${precision}</Precision>                 " + 
            "</xml>");
    String latitude = randomString();
    String longitude = randomString();
    String precision = randomString();
    
    @Test
    public void test() throws Exception {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("toUser", toUserName);
        params.put("fromUser", fromUserName);
        params.put("createTime", createTime);
        params.put("latitude", latitude);
        params.put("longitude", longitude);
        params.put("precision", precision);
        MockLocationEventService service = this.preparToTest(MockLocationEventService.class);
        Context context = this.doPostCtxt(template.replace(params));
        Mockito.when(service.doService(context)).thenReturn(expectReturn);
        
        String realReturn = handler.process(context);
        Assert.assertEquals(expectReturn, realReturn);
        Assert.assertEquals(latitude, service.Latitude);
        Assert.assertEquals(longitude, service.Longitude);
        Assert.assertEquals(precision, service.Precision);
        Mockito.verify(service).doService(context);
    }

    public static class MockLocationEventService extends LocationEventAdaptor {
        
    }
    
}
