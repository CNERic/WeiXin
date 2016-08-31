package com.bj58.hrg.investment.wx.vo.request;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class UserRemarkRequest implements Serializable {

    private static final long serialVersionUID = 984205924869413602L;

    @JSONField(name = "openid") private String openid;
    @JSONField(name = "remark") private String remark;
    
    public UserRemarkRequest() {
        
    }
    
    public UserRemarkRequest(String openId, String remark) {
        
        this.openid = openId;
        this.remark = remark;
    }
    
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}
