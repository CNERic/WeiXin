package cn.lzxz1234.weixin.api.wx.vo.message;

/**
 * @class TextMessage
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class TextMessage extends Message {

    private String Content; //消息id，64位整型

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
    
}
