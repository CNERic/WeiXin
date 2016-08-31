package com.bj58.hrg.investment.wx.vo.result;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @class BasicResult
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class BasicResult implements Serializable {
    
    private static final long serialVersionUID = 3379612749505864582L;
    
    @JSONField(name="errcode") private String errcode;
    @JSONField(name="errmsg") private String errmsg;
    
    public String getErrcode() {
        return errcode;
    }
    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }
    public String getErrmsg() {
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    
}
