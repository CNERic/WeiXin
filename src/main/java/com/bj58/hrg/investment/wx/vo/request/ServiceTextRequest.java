package com.bj58.hrg.investment.wx.vo.request;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class ServiceTextRequest implements Serializable {

    private static final long serialVersionUID = -8830796687755321701L;

    @JSONField(name = "touser") private String touser;
    @JSONField(name = "msgtype") private String msgtype = "text";
    @JSONField(name = "text") private Text text;
    
    public ServiceTextRequest() {
        
    }
    
    public ServiceTextRequest(String touser, String text) {
        
        this.touser = touser;
        this.text = new Text(text);
    }
    
    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public static class Text implements Serializable {
        
        private static final long serialVersionUID = 4893238125607481749L;
        
        @JSONField(name = "content") private String content;

        public Text() {
            
        }
        
        public Text(String content) {
            
            this.content = content;
        }
        
        public String getContent() {
            return content;
        }
        public void setContent(String content) {
            this.content = content;
        }
        
    }
    
}
