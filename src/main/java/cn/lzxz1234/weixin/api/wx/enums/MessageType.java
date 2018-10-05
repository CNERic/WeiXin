package cn.lzxz1234.weixin.api.wx.enums;

public enum MessageType {

    image, //1M，支持JPG格式 
    voice, //2M，播放长度不超过60s，支持AMR\MP3格式
    video, //10MB，支持MP4格式
    thumb, //64KB，支持JPG格式
    news;
    
}
