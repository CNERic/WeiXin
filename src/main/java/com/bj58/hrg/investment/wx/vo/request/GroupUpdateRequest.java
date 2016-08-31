package com.bj58.hrg.investment.wx.vo.request;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class GroupUpdateRequest implements Serializable {

    private static final long serialVersionUID = 4832154775550601113L;

    @JSONField(name="group") private Group group;
    
    public GroupUpdateRequest() {
        
    }
    
    public GroupUpdateRequest(String id, String name) {
        
        this.group = new Group(id, name);
    }
    
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }

    public static class Group implements Serializable {
        
        private static final long serialVersionUID = 630536379861906116L;
        
        @JSONField(name="id") private String id;
        @JSONField(name="name") private String name;
        
        public Group() {
            
        }
        
        public Group(String id, String name) {
            
            this.id = id;
            this.name = name;
        }
        
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
    
}
