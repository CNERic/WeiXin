package cn.lzxz1234.weixin.api.wx.vo.result;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.lzxz1234.weixin.api.wx.vo.MaterialArticle;
import com.alibaba.fastjson.annotation.JSONField;

public class MaterialGetResult extends BasicResult {

    private static final long serialVersionUID = -6707262666257660418L;

    @JSONField(name="total_count") private String totalCount;
    @JSONField(name="item_count") private String itemCount;
    @JSONField(name="item") private List<Item> item;
    
    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public static class Item implements Serializable {

        private static final long serialVersionUID = 7134311747350801053L;
        
        @JSONField(name="media_id") private String mediaId;
        @JSONField(name="content") private Content content;
        @JSONField(name="update_time") private Date updateTime;
        
        public String getMediaId() {
            return mediaId;
        }
        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }
        public Content getContent() {
            return content;
        }
        public void setContent(Content content) {
            this.content = content;
        }
        public Date getUpdateTime() {
            return updateTime;
        }
        public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
        }
        
    }
    
    public static class Content implements Serializable {

        private static final long serialVersionUID = 2045202791727341991L;
        
        @JSONField(name="news_item") private List<MaterialArticle> newsItem;
        @JSONField(name="name") private String name;
        @JSONField(name="url") private String url;
        
        public List<MaterialArticle> getNewsItem() {
            return newsItem;
        }
        public void setNewsItem(List<MaterialArticle> newsItem) {
            this.newsItem = newsItem;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getUrl() {
            return url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        
    }
    
}
