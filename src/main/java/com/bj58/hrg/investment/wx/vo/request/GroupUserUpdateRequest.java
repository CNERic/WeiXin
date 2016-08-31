package com.bj58.hrg.investment.wx.vo.request;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class GroupUserUpdateRequest implements Serializable {

    private static final long serialVersionUID = -2941630562132225661L;

    @JSONField(name = "openid") private String openId;
    @JSONField(name = "to_groupid") private String toGroupId;
    
    public GroupUserUpdateRequest() {
        
    }
    
    public GroupUserUpdateRequest(String openId, String toGroupId) {
        
        this.openId = openId;
        this.toGroupId = toGroupId;
    }
    
    public String getOpenId() {
        return openId;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public String getToGroupId() {
        return toGroupId;
    }
    public void setToGroupId(String toGroupId) {
        this.toGroupId = toGroupId;
    }
    
}
