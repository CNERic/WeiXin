package com.bj58.hrg.investment.wx.listener.impl.service.end;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

import com.bj58.hrg.investment.common.StringUtils;
import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.clust.JVMSynchronizer;
import com.bj58.hrg.investment.wx.dto.App;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.ioc.core.BeanFactory;
import com.bj58.hrg.investment.wx.ioc.core.FactoryBean;
import com.bj58.hrg.investment.wx.ioc.provider.AnnotationProvider;
import com.bj58.hrg.investment.wx.listener.ThreadsMode;
import com.bj58.hrg.investment.wx.listener.impl.process.SyncThreadMode;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.crypto.*", "org.apache.log4j.*"})
public abstract class ServiceTest {

    protected ThreadsMode handler;
    
    protected String toUserName = randomString();
    protected String fromUserName = randomString();
    protected String msgId = randomString();
    protected String createTime = Long.toString(System.currentTimeMillis());
    protected String expectReturn = randomString();
    
    protected String randomString() {
        
        return UUID.randomUUID().toString();
    }
    
    protected Context doGetCtxt(String params) {
        
        return doTestCtxt("GET", params, null);
    }
    
    protected Context doPostCtxt(String xmlContent) {
        
        return doTestCtxt("POST", null, xmlContent);
    }
    
    protected Context doPostCtxt(String params, String xmlContent) {
        
        return doTestCtxt("POST", params, xmlContent);
    }
    
    private Context doTestCtxt(String method, String params, String xmlContent) {
        
        Context context = new Context(decodeParams(params));
        context.setAttribute("method", method.toUpperCase());
        context.setAttribute("xmlContent", xmlContent);
        context.setAttribute("threadsMode", handler);
        return context;
    }
    
    private Map<String, Object> decodeParams(String paramstr) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(paramstr)) {
            String[] pair = paramstr.split("&");
            for(String each : pair) {
                String[] keyValue = each.split("=");
                if(keyValue.length == 2)
                    params.put(keyValue[0], keyValue[1]);
            }
        }
        return params;
    }
    
    public <T> T preparToTest(Class<T> clazz) throws Exception {
        
        T instance = Mockito.mock(clazz);
        BeanFactory factory = new BeanFactory();
        factory.regist(BeanFactory.class.getName(), factory);
        new AnnotationProvider<Node>("com.bj58.hrg.investment.wx.listener", Node.class).registTo(factory);
        new AnnotationProvider<Node>(App.getConfig("weixin.service.package"), Node.class).registTo(factory);
        
        this.registIfNotExists(factory, "lock", JVMSynchronizer.class, true, "");
        this.registIfNotExists(factory, "root", SyncThreadMode.class, true, "init");
        factory.replace(clazz.getSuperclass().getName(), instance);
        FactoryBean<ThreadsMode> factoryBean = factory.get("root");
        handler = factoryBean.get();
        return instance;
    }
    
    private void registIfNotExists(BeanFactory factory, String name, Class<?> clazz, boolean singleton, String init) {
        
        if(factory.exists(name)) return;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", clazz.getName());
        params.put("singleton", singleton);
        params.put("init", init);
        factory.regist(name, params);
    }
}
