package com.bj58.hrg.investment.wx.vo.request;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class GroupUserQueryRequest implements Serializable {

    private static final long serialVersionUID = -860766406848520514L;
    
    @JSONField(name = "openid") private String openId;

    public GroupUserQueryRequest() {
        
    }
    
    public GroupUserQueryRequest(String openId) {
        
        this.openId = openId;
    }
    
    public String getOpenId() {
        return openId;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
}
