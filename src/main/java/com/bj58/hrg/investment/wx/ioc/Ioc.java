package com.bj58.hrg.investment.wx.ioc;

import com.bj58.hrg.investment.common.Lang;
import com.bj58.hrg.investment.wx.annotation.Node;
import com.bj58.hrg.investment.wx.dto.App;
import com.bj58.hrg.investment.wx.ioc.core.BeanFactory;
import com.bj58.hrg.investment.wx.ioc.provider.AnnotationProvider;
import com.bj58.hrg.investment.wx.ioc.provider.JsonIocProvider;


public class Ioc {

    private BeanFactory factory;
    
    public Ioc() {
        
        factory = new BeanFactory();
        factory.regist("beanFactory", factory);
        new AnnotationProvider<Node>("com.bj58.hrg.investment.wx.listener", Node.class).registTo(factory);
        new AnnotationProvider<Node>(App.getConfig("weixin.service.package"), Node.class).registTo(factory);
        new JsonIocProvider(new String(Lang.loadFromClassPath("/weixin.js"))).registTo(factory);
    }
    
}
