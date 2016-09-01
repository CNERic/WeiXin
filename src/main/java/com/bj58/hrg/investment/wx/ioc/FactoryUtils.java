package com.bj58.hrg.investment.wx.ioc;

import com.bj58.hrg.investment.wx.ioc.core.BeanFactory;
import com.bj58.hrg.investment.wx.ioc.core.FactoryBean;

public final class FactoryUtils {

    private static BeanFactory factory;
    
    private FactoryUtils() {
        
    }
    
    public static void setFactory(BeanFactory factory) {
        
        FactoryUtils.factory = factory;
    }
    
    public static <T> T getInstance(Class<T> clazz) throws Exception {
        
        return getInstance(clazz.getName());
    }
    
    public static <T> T getInstance(String name) throws Exception {
        
        if(factory == null) 
            throw new RuntimeException("Bean 工厂未初始化");
            
        FactoryBean<T> factoryBean = factory.get(name);
        return factoryBean.get();
    }
    
}
