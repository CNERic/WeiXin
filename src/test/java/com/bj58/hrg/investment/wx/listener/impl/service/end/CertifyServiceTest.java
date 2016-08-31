package com.bj58.hrg.investment.wx.listener.impl.service.end;

import org.junit.Test;
import org.mockito.Mockito;

import com.bj58.hrg.investment.wx.dto.Context;

import junit.framework.Assert;

public class CertifyServiceTest extends ServiceTest {

    private String url = "signature=%s&timestamp=%s&nonce=%s&echostr=%s";
    
    @Test
    public void test() throws Exception {

        String signature = "aab54d32bc6ba920a090952ad26e2a002e5541a6";
        String timestamp = "20150917111708";
        String nonce = "nonce";
        String echostr = "succ";
        String params = String.format(url, signature, timestamp, nonce, echostr);
        MockCertifyService service = this.preparToTest(MockCertifyService.class);
        
        Context context = this.doGetCtxt(params);
        Mockito.when(service.doService(context)).thenCallRealMethod();
        
        Assert.assertEquals(echostr, handler.process(context));
        Mockito.verify(service).doService(context);
    }

    public static class MockCertifyService extends CertifyService {

        @Override
        public String doService(Context context) throws Exception {
            
            return super.doService(context);
        }
        
    }
    
}
