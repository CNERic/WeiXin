package com.bj58.hrg.investment.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.bj58.hrg.investment.wx.vo.Group;

/**
 * @class GroupCreateResult
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class GroupCreateResult extends BasicResult {

    private static final long serialVersionUID = -3426481289162868652L;
    
    @JSONField(name="group") private Group group;
    
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
    
}
