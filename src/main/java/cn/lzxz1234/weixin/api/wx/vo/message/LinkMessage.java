package cn.lzxz1234.weixin.api.wx.vo.message;

/**
 * @class LinkMessage
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class LinkMessage extends Message {

    private String Title;
    private String Description;
    private String Url;
    
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public String getUrl() {
        return Url;
    }
    public void setUrl(String url) {
        Url = url;
    }
    
}
