package com.bj58.hrg.investment.wx;

import com.bj58.hrg.investment.common.Lang;
import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.annotation.Service;
import com.bj58.hrg.investment.wx.dto.App;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.ioc.core.BeanFactory;
import com.bj58.hrg.investment.wx.ioc.core.FactoryBean;
import com.bj58.hrg.investment.wx.ioc.provider.AnnotationProvider;
import com.bj58.hrg.investment.wx.ioc.provider.JsonIocProvider;
import com.bj58.hrg.investment.wx.listener.ThreadsMode;

/**
 * @class MessageHandler
 * @author lzxz1234
 * @description
 * @version v1.0
 */
public class MessageHandler {

    private ThreadsMode proxy;
    private BeanFactory factory;
    
    public MessageHandler() throws Exception {
        
        factory = new BeanFactory();
        factory.regist(BeanFactory.class.getName(), factory);
        new AnnotationProvider<Node>("com.bj58.hrg.investment.wx.listener", Node.class).registTo(factory);
        new AnnotationProvider<Service>("com.bj58.hrg.investment.wx.api", Service.class).registTo(factory);
        new AnnotationProvider<Node>(App.getConfig("weixin.service.package"), Node.class).registTo(factory);
        new JsonIocProvider(new String(Lang.loadFromClassPath("/weixin.js"))).registTo(factory);
        FactoryBean<ThreadsMode> factoryBean = factory.get("root");
        proxy = factoryBean.get();
    }

    public String process(Context context) throws Exception {

        context.setAttribute("threadsMode", proxy);
        return proxy.process(context);
    }

}
