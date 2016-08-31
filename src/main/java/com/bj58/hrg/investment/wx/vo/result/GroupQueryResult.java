package com.bj58.hrg.investment.wx.vo.result;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.bj58.hrg.investment.wx.vo.Group;

public class GroupQueryResult extends BasicResult {

    private static final long serialVersionUID = -3968413508337662979L;
    
    @JSONField(name="groups") private List<Group> groups;
    
    public List<Group> getGroups() {
        return groups;
    }
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    
}
