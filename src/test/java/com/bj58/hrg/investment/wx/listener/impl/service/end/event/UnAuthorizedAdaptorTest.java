package com.bj58.hrg.investment.wx.listener.impl.service.end.event;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.bj58.hrg.investment.common.StringTemplate;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.impl.service.end.ServiceTest;

public class UnAuthorizedAdaptorTest extends ServiceTest {

    StringTemplate stp = StringTemplate.compile(
            "<xml>                              " + 
            "<AppId>${appId}</AppId>                    " + 
            "<CreateTime>${createTime}</CreateTime>" + 
            "<InfoType>unauthorized</InfoType>             " + 
            "<AuthorizerAppid>${authAppId}</AuthorizerAppid>" + 
            "</xml>");
    String appId = randomString();
    String authAppId = randomString();
    
    @Test
    public void test() throws Exception {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("createTime", createTime);
        params.put("appId", appId);
        params.put("authAppId", authAppId);
        MockUnAuthorizedService service = this.preparToTest(MockUnAuthorizedService.class);
        Context context = this.doPostCtxt(stp.replace(params));
        Mockito.when(service.doService(context)).thenReturn(expectReturn);
        
        String realReturn = handler.process(context);
        Assert.assertEquals(expectReturn, realReturn);
        Assert.assertEquals(createTime, service.CreateTime);
        Assert.assertEquals(appId, service.AppId);
        Assert.assertEquals(authAppId, service.AuthorizerAppid);
        Mockito.verify(service).doService(context);
    }

    public static class MockUnAuthorizedService extends UnAuthorizedAdaptor {
        
    }
}
