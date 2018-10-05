package cn.lzxz1234.weixin.api.wx.listener;

import cn.lzxz1234.weixin.api.wx.dto.Context;

/**
 * @class Service
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public interface Service {

    String DEFAULT_RETURN = "";
    
    String doService(Context context) throws Exception;
    
}
