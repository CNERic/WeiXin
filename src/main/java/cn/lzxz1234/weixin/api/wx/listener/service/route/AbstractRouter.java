package cn.lzxz1234.weixin.api.wx.listener.service.route;

import cn.lzxz1234.weixin.api.wx.dto.Context;
import cn.lzxz1234.weixin.api.wx.listener.Service;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * @author lzxz1234
 * @version v1.0
 * @class AbstractRouter
 */
public abstract class AbstractRouter {

    Logger log = Logger.getLogger(this.getClass());
    Map<String, Service> nexts = new HashMap<>();

    synchronized void put(String key, Service value) {

        if(!nexts.containsKey(key))
            this.nexts.put(key, value);
        else
            log.warn("结点 " + key  + " 重复定义");
    }

    String doNext(String key, Context context) throws Exception {

        Service next = this.nexts.get(key);
        if(next == null) {
            log.warn(this.getClass().getSimpleName() + " 根据 " + key + " 找不到下级结点");
        } else {
            try {
                return next.doService(context);
            } catch (Exception e) {
                log.error(next.getClass().getSimpleName() + " 处理失败啊", e);
            }
        }
        return "";
    }

}
