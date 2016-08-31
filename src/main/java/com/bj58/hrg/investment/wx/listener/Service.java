package com.bj58.hrg.investment.wx.listener;

import com.bj58.hrg.investment.wx.dto.Context;

/**
 * @class Service
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public interface Service {

    public static final String DEFAULT_RETURN = "";
    
    /**
     * @description service
     * @param req
     * @param resp
     * @return 
    */
    public String doService(Context context) throws Exception;
    
}
