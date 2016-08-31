package com.bj58.hrg.investment.wx.vo.request;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.bj58.hrg.investment.wx.vo.NewsArticle;

public class ServiceNewsRequest implements Serializable {

    private static final long serialVersionUID = -6529392182119914411L;

    @JSONField(name = "touser") private String touser;
    @JSONField(name = "msgtype") private String msgtype = "news";
    @JSONField(name = "news") private List<NewsArticle> news;
    
    public ServiceNewsRequest() {
        
    }
    
    public ServiceNewsRequest(String touser, List<NewsArticle> articles) {
        
        this.touser = touser;
        this.news = articles;
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
    public List<NewsArticle> getNews() {
        return news;
    }
    public void setNews(List<NewsArticle> news) {
        this.news = news;
    }
    
}
