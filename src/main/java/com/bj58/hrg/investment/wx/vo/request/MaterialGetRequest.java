package com.bj58.hrg.investment.wx.vo.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.bj58.hrg.investment.wx.enums.MessageType;

public class MaterialGetRequest {

    @JSONField(name="type") private String type;
    @JSONField(name="offset") private int offset;
    @JSONField(name="count") private int count;
    
    public MaterialGetRequest() {
        
    }
    
    public MaterialGetRequest(MessageType type, int offset, int count) {
        
        this.type = type.name();
        this.offset = offset;
        this.count = count;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    
}
