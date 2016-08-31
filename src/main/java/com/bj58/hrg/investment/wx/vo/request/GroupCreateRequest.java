package com.bj58.hrg.investment.wx.vo.request;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class GroupCreateRequest implements Serializable {

    private static final long serialVersionUID = -4264348537927880962L;
    
    @JSONField(name = "group") private Group group;
    
    public GroupCreateRequest() {
        
    }
    
    public GroupCreateRequest(String name) {
        
        this.group = new Group(name);
    }
    
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }

    public static class Group implements Serializable {
        
        private static final long serialVersionUID = 1003961084958634416L;
        
        @JSONField(name = "name") private String name;

        public Group() {
            
        }
        
        public Group(String name) {
            
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        
    }
}
