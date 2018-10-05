package cn.lzxz1234.weixin.api.wx.dto;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @class Context
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class Context {
    
    private static final Logger log = Logger.getLogger(Context.class);
    
    private Map<String, Object> map;
    
    public Context() {
        
        this.map = new HashMap<>();
    }
    
    public Context(Map<String, Object> orginalParams) {
        
        this.map = orginalParams;
    }
    
    public void setAttribute(String key, Object value) {
        
        this.map.put(key, value);
    }
    
    public void addAttribute(String key, Object value) {
        
        Object previous = this.map.put(key, value);
        if(previous != null) 
            log.info(String.format("参数[%s]原值[%s]被[%s]覆盖！", key, previous, value));
    }
    
    public void removeAttribute(String key) {
        
        this.map.remove(key);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getAttribute(Class<T> clazz, String key) {
        
        return (T)getAttribute(key);
    }
    
    public Object getAttribute(String key) {
        
        if(key == null) return null;
        return this.map.get(key);
    }

    public String getString(String key) {

        if(key == null) return null;
        return (String)this.map.get(key);
    }
    
    public Object getAttribute(String key, String defaultValue) {
        
        if(key == null) return null;
        Object result = this.map.get(key);
        return result == null ? defaultValue : result;
    }
    
    public Map<String, Object> getAttributes() {
        
        return new HashMap<>(map);
    }
    
}
