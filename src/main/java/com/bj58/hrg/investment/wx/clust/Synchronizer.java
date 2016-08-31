package com.bj58.hrg.investment.wx.clust;

import java.nio.charset.Charset;

import com.alibaba.fastjson.JSON;

public abstract class Synchronizer {

    protected final Charset UTF8 = Charset.forName("UTF-8");
    
    public abstract void lock(String key);
    
    public abstract void unlock(String key);
    
    public abstract void globalSet(byte[] key, byte[] value);
    
    public abstract byte[] globalGet(byte[] key);
    
    public void globalSet(String key, Object object) {
        
        if(key == null || object == null) throw new NullPointerException();
        String value = JSON.toJSONString(object);
        this.globalSet(key.getBytes(UTF8), value.getBytes(UTF8));
    }
    
    public <T> T globalGet(Class<T> clazz, String key) {
        
        byte[] value = this.globalGet(key.getBytes(UTF8));
        if(value == null || value.length == 0) return null;
        return JSON.parseObject(new String(value, UTF8), clazz);
    }
}
