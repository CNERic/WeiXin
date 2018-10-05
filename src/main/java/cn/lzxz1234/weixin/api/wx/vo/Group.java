package cn.lzxz1234.weixin.api.wx.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @class Group
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class Group implements Serializable {

    private static final long serialVersionUID = -4805435746290456661L;
    
    @JSONField(name="id") private String id;
    @JSONField(name="name") private String name;
    @JSONField(name="count") private Integer count;
    
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
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    
}
