package com.bj58.hrg.investment.wx;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.common.FileUtils;
import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.annotation.Singleton;
import com.bj58.hrg.investment.wx.clust.JVMSynchronizer;
import com.bj58.hrg.investment.wx.dto.App;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.ioc.core.BeanFactory;
import com.bj58.hrg.investment.wx.ioc.core.FactoryBean;
import com.bj58.hrg.investment.wx.ioc.provider.AnnotationProvider;
import com.bj58.hrg.investment.wx.ioc.provider.JsonIocProvider;
import com.bj58.hrg.investment.wx.listener.ThreadsMode;
import com.bj58.hrg.investment.wx.listener.impl.process.SyncThreadMode;

/**
 * @class MessageHandler
 * @author lzxz1234
 * @description
 * @version v1.0
 */
public class MessageHandler {
    
    private Logger log = Logger.getLogger(MessageHandler.class);
    private ThreadsMode proxy;
    private BeanFactory factory;
    
    public MessageHandler() throws Exception {
        
        this.initBase();
        this.fillRequired();
    }

    public MessageHandler(String confPath) throws Exception {
        
        this.initBase();
        log.info("WeiXin 加载配置文件：" + confPath);
        new JsonIocProvider(new String(FileUtils.readToByteArray(confPath))).registTo(factory);
        this.fillRequired();
        
    }
    
    private void fillRequired() throws Exception {
        
        this.registIfNotExists("lock", JVMSynchronizer.class, true, "");
        this.registIfNotExists("root", SyncThreadMode.class, true, "init");
        FactoryBean<ThreadsMode> factoryBean = factory.get("root");
        proxy = factoryBean.get();
    }
    
    private void initBase() throws Exception {
        
        factory = new BeanFactory();
        factory.regist(BeanFactory.class.getName(), factory);
        new AnnotationProvider<Node>("com.bj58.hrg.investment.wx.listener", Node.class).registTo(factory);
        new AnnotationProvider<Singleton>("com.bj58.hrg.investment.wx.api", Singleton.class).registTo(factory);
        new AnnotationProvider<Node>(App.getConfig("weixin.service.package"), Node.class).registTo(factory);
    }
    
    public String process(Context context) throws Exception {

        context.setAttribute("threadsMode", proxy);
        return proxy.process(context);
    }

    private void registIfNotExists(String name, Class<?> clazz, boolean singleton, String init) {
        
        if(factory.exists(name)) return;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", clazz.getName());
        params.put("singleton", singleton);
        params.put("init", init);
        this.factory.regist(name, params);
    }
}
