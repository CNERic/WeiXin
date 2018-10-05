package cn.lzxz1234.weixin.api.wx.vo.request;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import cn.lzxz1234.weixin.api.wx.vo.Button;

public class MenuCreateRequest implements Serializable {

    private static final long serialVersionUID = -6241503716425601035L;

    @JSONField(name = "button") private List<Button> button;

    public MenuCreateRequest() {
        
    }
    
    public MenuCreateRequest(List<Button> buttons) {
        
        this.button = buttons;
    }
    
    public List<Button> getButton() {
        return button;
    }
    public void setButton(List<Button> button) {
        this.button = button;
    } 
    
}
