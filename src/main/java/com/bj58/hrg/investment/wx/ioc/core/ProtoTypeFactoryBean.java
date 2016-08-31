package com.bj58.hrg.investment.wx.ioc.core;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bj58.hrg.investment.common.FieldUtils;
import com.bj58.hrg.investment.common.InvokeUtils;
import com.bj58.hrg.investment.common.Lang;
import com.bj58.hrg.investment.common.MethodUtils;
import com.bj58.hrg.investment.common.StringUtils;
import com.bj58.hrg.investment.wx.annotation.Autowired;

public class ProtoTypeFactoryBean<T> extends FactoryBean<T> {

    public ProtoTypeFactoryBean(BeanFactory context) {
        super(context);
    }

    private Class<?> type;
    private FactoryBean<?>[] args;
    private Map<String, FactoryBean<?>> fields = new HashMap<String, FactoryBean<?>>();
    private List<Field> autowired; 
    private String init;
    
    @Override
    @SuppressWarnings("unchecked")
    public T get() throws Exception{

        Object result;
        if(args == null)
            result = type.newInstance();
        else {
            Class<?>[] argTypes = new Class<?>[args.length];
            Object[] argObjects = new Object[args.length];
            for(int i = 0; i < args.length; i ++) {
                argTypes[i] = args[i].getType();
                argObjects[i] = args[i].get();
            }
            result = MethodUtils.getConstructor(type, argTypes).newInstance(argObjects);
        }
        
        if(fields != null) {
            Iterator<Entry<String, FactoryBean<?>>> it = fields.entrySet().iterator();
            while(it.hasNext()) {
                Entry<String, FactoryBean<?>> entry = it.next();
                InvokeUtils.setFieldValue(result, entry.getKey(), entry.getValue().get());
            }
        }
        
        for(Field each : autowired) {
            Autowired anno = each.getAnnotation(Autowired.class);
            String name = "".equals(anno.value()) ? each.getType().getName() : anno.value();
            each.set(result, context.get(name).get());
        }
        if(!StringUtils.isEmpty(init))
            InvokeUtils.invoke(result, init);
        return (T) result;
    }

    @Override
    public Class<?> getType() {
        
        return type;
    }
    
    public void setType(Object type) {
        
        if(type == null)
            throw new NullPointerException("type 不允许为空");
        else if(type instanceof Class)
            this.type = (Class<?>)type;
        else if(type instanceof String) 
            this.type = Lang.forName((String) type);
        else
            throw new IllegalArgumentException("不识别的 type：" + type);
        
        this.autowired = FieldUtils.traverseGetFields(this.type, Autowired.class);
    }
    
    public void setInit(Object init) {
        
        if(init == null) return;
        if(init instanceof String)
            this.init = (String)init;
        else
            throw new IllegalArgumentException("初始化设置只允许字符串，但实际为 :" + init.getClass());
    }
    
    public void setArgs(Object params) {
        
        if(params == null) return;
        if(params instanceof Collection) {
            List<?> paramList = (List<?>) params;
            args = new FactoryBean[paramList.size()];
            for(int i = 0; i < paramList.size(); i ++) 
                args[i] = context.from(paramList.get(i));
        } else {
            args = new FactoryBean[1];
            args[0] = context.from(params);
        }
    }
    
    public void setFields(Object params) {
        
        if(params == null) return;
        if(!(params instanceof Map))
            throw new IllegalArgumentException("fields 不允许非 map 类型");
        Map<?, ?> paramMap = (Map<?, ?>) params;
        Iterator<?> it = paramMap.entrySet().iterator();
        while(it.hasNext()) {
            Entry<?, ?> entry = (Entry<?, ?>) it.next();
            fields.put((String) entry.getKey(), context.from(entry.getValue()));
        }
    }

}
