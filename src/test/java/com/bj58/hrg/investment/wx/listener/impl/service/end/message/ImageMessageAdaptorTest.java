package com.bj58.hrg.investment.wx.listener.impl.service.end.message;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;

import com.bj58.hrg.investment.common.StringTemplate;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.impl.service.end.ServiceTest;

import junit.framework.Assert;

public class ImageMessageAdaptorTest extends ServiceTest {

    StringTemplate stp = StringTemplate.compile(
            "<xml>                                            " + 
            "<ToUserName><![CDATA[${toUser}]]></ToUserName>      " + 
            "<FromUserName><![CDATA[${fromUser}]]></FromUserName>" + 
            "<CreateTime>${createTime}</CreateTime>              " + 
            "<MsgType><![CDATA[image]]></MsgType>             " + 
            "<PicUrl><![CDATA[${picUrl}]]></PicUrl>       " + 
            "<MediaId><![CDATA[${media_id}]]></MediaId>          " + 
            "<MsgId>${msgId}</MsgId>                  " + 
            "</xml>");
    String picUrl = randomString();
    String mediaId = randomString();

    @Test
    public void testRaw() throws Exception {
        
        MockImageMessageService service = this.preparToTest(MockImageMessageService.class);
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("toUser", toUserName);
        params.put("fromUser", fromUserName);
        params.put("createTime", createTime);
        params.put("picUrl", picUrl);
        params.put("media_id", mediaId);
        params.put("msgId", msgId);
        Context context = this.doPostCtxt(stp.replace(params));
        Mockito.when(service.doService(context)).thenReturn(expectReturn);
        
        String realReturn = handler.process(context);
        Assert.assertEquals(expectReturn, realReturn);
        Assert.assertEquals(picUrl, service.PicUrl);
        Assert.assertEquals(mediaId, service.MediaId);
        Assert.assertEquals(msgId, service.MsgId);
        Mockito.verify(service).doService(context);
    }

    public static class MockImageMessageService extends ImageMessageAdaptor {

        @Override
        public String doService(Context context) throws Exception {
            return super.doService(context);
        }
        
    }
    
}
