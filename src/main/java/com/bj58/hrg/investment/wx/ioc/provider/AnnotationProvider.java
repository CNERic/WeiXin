package com.bj58.hrg.investment.wx.ioc.provider;

import java.lang.annotation.Annotation;

import com.bj58.hrg.investment.common.Scans;
import com.bj58.hrg.investment.common.StringUtils;
import com.bj58.hrg.investment.wx.ioc.core.BeanFactory;

public class AnnotationProvider<A extends Annotation> implements IocProvider {

    private String[] packages;
    private Class<A> anno;
    
    public AnnotationProvider(String packages, Class<A> anno) {
        
        this.packages = packages.split("\\|");
        this.anno = anno;
    }

    @Override
    public void registTo(BeanFactory factory) {
        
        for(String each : packages) 
            if(!StringUtils.isEmpty(each))
                for(Class<?> clazz : Scans.getClasses(each)) 
                    if(clazz.getAnnotation(anno) != null)
                        factory.regist(clazz.getName(), clazz);
    }

}
