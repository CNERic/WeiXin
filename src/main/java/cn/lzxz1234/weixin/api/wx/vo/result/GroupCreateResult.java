package cn.lzxz1234.weixin.api.wx.vo.result;

import cn.lzxz1234.weixin.api.wx.vo.Group;
import com.alibaba.fastjson.annotation.JSONField;

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
