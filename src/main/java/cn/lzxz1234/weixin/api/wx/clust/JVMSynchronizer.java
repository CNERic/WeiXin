package cn.lzxz1234.weixin.api.wx.clust;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JVMSynchronizer extends Synchronizer {

    private Lock lock = new ReentrantLock();
    private Map<String, String> map = new HashMap<String, String>();
    
    @Override
    public void lock(String key) {
        
        this.lock.lock();
    }

    @Override
    public void unlock(String key) {
        
        this.lock.unlock();
    }

    @Override
    public void set(byte[] key, byte[] value) {
        
        this.map.put(new String(key, UTF8), new String(value, UTF8));
    }

    @Override
    public byte[] get(byte[] key) {
        
        return this.map.containsKey(new String(key, UTF8)) ? this.map.get(new String(key, UTF8)).getBytes(UTF8) : null;
    }

}
