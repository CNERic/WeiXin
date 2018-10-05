package cn.lzxz1234.weixin.api.wx;

import cn.lzxz1234.weixin.api.wx.api.*;
import cn.lzxz1234.weixin.api.wx.clust.JVMSynchronizer;
import cn.lzxz1234.weixin.api.wx.clust.Synchronizer;
import cn.lzxz1234.weixin.api.wx.dto.App;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author lzxz1234
 * @version v1.0
 * @class WeiXinConfigure
 */
@Configuration
@EnableConfigurationProperties(WeiXinProperties.class)
public class WeiXinConfigure implements ApplicationContextAware {

    ApplicationContext applicationContext;

    @Autowired
    private WeiXinProperties properties;

    @Bean
    @ConditionalOnProperty(prefix = "cn.lzxz1234.weixin", value = "enabled", havingValue = "true")
    public WeiXinHandler weixinHandler(){

        System.out.println("===============================================");
        System.out.println("==================  WeiXin  ===================");
        System.out.println("===============================================");
        return  new WeiXinHandler();
    }

    @Bean
    public Synchronizer synchronizer() {

        return new JVMSynchronizer();
    }

    @Bean
    @ConditionalOnProperty(prefix = "cn.lzxz1234.weixin", value = "enabled", havingValue = "true")
    public TokenAccessor tokenAccessor() {

        return new TokenAccessor(applicationContext.getBean(Synchronizer.class));
    }

    @Bean
    @ConditionalOnBean(TokenAccessor.class)
    public GroupManager groupManager() {

        return new GroupManager(applicationContext.getBean(TokenAccessor.class));
    }

    @Bean
    @ConditionalOnBean(TokenAccessor.class)
    public MaterialManager materialManager() {

        return new MaterialManager(applicationContext.getBean(TokenAccessor.class));
    }

    @Bean
    @ConditionalOnBean(TokenAccessor.class)
    public MenuManager menuManager() {

        return new MenuManager(applicationContext.getBean(TokenAccessor.class));
    }

    @Bean
    @ConditionalOnBean(TokenAccessor.class)
    public UserManager userManager() {

        return new UserManager(applicationContext.getBean(TokenAccessor.class));
    }

    @Bean
    @ConditionalOnBean(TokenAccessor.class)
    public UrlTransformer urlTransformer() {

        return new UrlTransformer(applicationContext.getBean(TokenAccessor.class));
    }

    @Bean
    @ConditionalOnBean(TokenAccessor.class)
    public ServiceMessageSender serviceMessageSender() {

        return new ServiceMessageSender(applicationContext.getBean(TokenAccessor.class));
    }

    @Bean
    @ConditionalOnProperty(prefix = "cn.lzxz1234.weixin.platform ", value = "enabled", havingValue = "true")
    public PlatFormTokenAccessor platFormTokenAccessor() {

        return applicationContext.getBeansOfType(Synchronizer.class).size() == 0 ?
                new PlatFormTokenAccessor(new JVMSynchronizer()) :
                new PlatFormTokenAccessor(applicationContext.getBean(Synchronizer.class));
    }

    @Bean
    @ConditionalOnBean(PlatFormTokenAccessor.class)
    public PlatFormManager platFormManager() {

        return new PlatFormManager(applicationContext.getBean(Synchronizer.class),
                applicationContext.getBean(PlatFormTokenAccessor.class));
    }

    @Bean
    @ConditionalOnBean(PlatFormManager.class)
    public PlatFormMenuManager platFormMenuManager() {

        return new PlatFormMenuManager(applicationContext.getBean(PlatFormManager.class));
    }

    @PostConstruct
    public void init() {

        App.Info.aesKey = properties.getAeskey();
        App.Info.id = properties.getId();
        App.Info.name = properties.getName();
        App.Info.secret = properties.getSecret();
        App.Info.token = properties.getToken();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
    }
}
