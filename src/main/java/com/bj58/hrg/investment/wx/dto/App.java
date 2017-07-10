package com.bj58.hrg.investment.wx.dto;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.common.Cfg;

/**
 * @class AppInfo
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class App {

    private static Logger log = Logger.getLogger(App.class);
    private static Cfg cfg;
    
    static {
        try {
            cfg = Cfg.getClassPathCfg("/weixin.properties");
            Info.reInit();
        } catch (Exception e) {
            log.warn("从 classpath 加载 weixin.properties 配置文件失败，请手动初始化 App.init()");
        }
    }
    
    public static void init(String path) {
        
        cfg = Cfg.getFileSystemCfg(path);
        Info.reInit();
    }
    
    public static String getConfig(String key) {
        
        return cfg.get(key);
    }
    
    public static class Info {
        
        /**
         * id: appId
         */
        public static String id;
        public static String name;
        public static String secret;
        public static String aesKey;
        public static String token;
        public static String loginedUrl;
        
        static void reInit() {
            id = cfg.get("weixin.app.id");
            name = cfg.get("weixin.app.name");
            secret = cfg.get("weixin.app.secret");
            aesKey = cfg.get("weixin.app.aeskey");
            token = cfg.get("weixin.app.token");
            loginedUrl = cfg.get("weixin.thirdparty.logined.redirect");
        }
    }
}
