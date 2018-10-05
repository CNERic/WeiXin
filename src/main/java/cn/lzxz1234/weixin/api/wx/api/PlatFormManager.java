package cn.lzxz1234.weixin.api.wx.api;

import cn.lzxz1234.weixin.api.common.HttpUtils;
import cn.lzxz1234.weixin.api.common.StringTemplate;
import cn.lzxz1234.weixin.api.wx.clust.Synchronizer;
import cn.lzxz1234.weixin.api.wx.dto.App;
import cn.lzxz1234.weixin.api.wx.vo.request.*;
import cn.lzxz1234.weixin.api.wx.vo.result.*;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 公众号第三方平台可用主动调用
 * @class PlatFormManager
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class PlatFormManager {

    private static Logger log = Logger.getLogger(PlatFormManager.class);
    
    private static StringTemplate getLoginUrl = StringTemplate.compile(WeiXinURL.PLATFORM_LOGINURL);
    private static StringTemplate getAuthInfoUrl = StringTemplate.compile(WeiXinURL.PLATFORM_GET_AUTHINFO);
    private static StringTemplate getAuthAccessTokenUrl = StringTemplate.compile(WeiXinURL.PLATFORM_GET_ACCESSTOKEN);
    private static StringTemplate getAuthorizerInfoUrl = StringTemplate.compile(WeiXinURL.PLATFORM_GET_AUTHORIZER_INFO);
    private static StringTemplate getAuthorizerOptionUrl = StringTemplate.compile(WeiXinURL.PLATFORM_GET_AUTHORIZER_OPTION);
    private static StringTemplate setAuthorizerOptionUrl = StringTemplate.compile(WeiXinURL.PLATFORM_SET_AUTHORIZER_OPTION);
    private static StringTemplate getPreAuthCodeUrl = StringTemplate.compile(WeiXinURL.PLATFORM_GET_PRE_AUTHCODE);

    private Synchronizer synchronizer;
    private PlatFormTokenAccessor platFormTokenAccessor;
    private PlatFormGetPreAuthCodeResult result;

    public PlatFormManager(Synchronizer synchronizer, PlatFormTokenAccessor platFormTokenAccessor) {

        this.synchronizer = synchronizer;
        this.platFormTokenAccessor = platFormTokenAccessor;
    }
    
    public String getLoginUrl() {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("component_appid", App.Info.id);
        params.put("pre_auth_code", getPreAuthCode());
        params.put("loogined_redirect_url", App.Info.loginedUrl);
        return getLoginUrl.replace(params);
    }
    
    /**
     * 该API用于获取预授权码。预授权码用于公众号授权时的第三方平台方安全验证。
     * @return 
     */
    public String getPreAuthCode() {
        
        if(result == null || System.currentTimeMillis() > result.getExpiresIn()) {
            try {
                synchronizer.lock(this.getClass().getName());
                result = synchronizer.get(PlatFormGetPreAuthCodeResult.class, getPlatFormManagerKey());
                if(result == null || System.currentTimeMillis() > result.getExpiresIn()) {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("component_access_token", platFormTokenAccessor.getAccessToken());
                    String urlLocation = getPreAuthCodeUrl.replace(params);
                    
                    String reqJson = JSON.toJSONString(new PlatformGetPreAuthCodeRequest(App.Info.id));
                    String respJson = HttpUtils.post(urlLocation, reqJson);
                    result = JSON.parseObject(respJson, PlatFormGetPreAuthCodeResult.class);
                    result.setExpiresIn(System.currentTimeMillis() + result.getExpiresIn() * 900);
                    log.info("更新 PreAuthCode：" + result.getPreAuthCode());
                    synchronizer.set(getPlatFormManagerKey(), result);
                }
            } finally {
                synchronizer.unlock(this.getClass().getName());
            }
        }
        return result.getPreAuthCode();
    }
    
    /**
     * 该API用于使用授权码换取授权公众号的授权信息，并换取authorizer_access_token和
     * authorizer_refresh_token。 授权码的获取，需要在用户在第三方平台授权页中完成授
     * 权流程后，在回调URI中通过URL参数提供给第三方平台方。
     * @param authCode
     * @return 
     */
    public PlatFormGetAuthInfoResult getAuthInfo(String authCode) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("component_access_token", platFormTokenAccessor.getAccessToken());
        String urlLocation = getAuthInfoUrl.replace(params);
        String postContent = JSON.toJSONString(new PlatformGetAuthInfoRequest(App.Info.id, authCode));
        String respJson = HttpUtils.post(urlLocation, postContent);
        return JSON.parseObject(respJson, PlatFormGetAuthInfoResult.class);
    }
    
    /**
     * 该API用于在授权方令牌（authorizer_access_token）失效时，可用刷新令牌
     * （authorizer_refresh_token）获取新的令牌。
     * @param authAppId 授权方appid
     * @param refreshToken 授权方的刷新令牌，刷新令牌主要用于公众号第三方平台获取和刷新已
     * 授权用户的access_token，只会在授权时刻提供，请妥善保存。 一旦丢失，只能让用户重新授
     * 权，才能再次拿到新的刷新令牌
     * @return
     */
    public PlatFormAccessTokenResult getAuthAccessToken(String authAppId,
                                                        String refreshToken) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("component_access_token", platFormTokenAccessor.getAccessToken());
        String urlLocation = getAuthAccessTokenUrl.replace(params);
        String postContent = JSON.toJSONString(new PlatformRefreshAccessTokenRequest(App.Info.id, authAppId, refreshToken));
        String respJson = HttpUtils.post(urlLocation, postContent);
        return JSON.parseObject(respJson, PlatFormAccessTokenResult.class);
    }
    
    /**
     * 该API用于获取授权方的公众号基本信息，包括头像、昵称、帐号类型、认证类型、微信号、原始ID和二维码图片URL。
     * @param authAppId 授权方appid
     * @return
     */
    public PlatFormGetAuthorizerInfoResult getAuthorizerInfo(String authAppId) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("component_access_token", platFormTokenAccessor.getAccessToken());
        String urlLocation = getAuthorizerInfoUrl.replace(params);
        String postContent = JSON.toJSONString(new PlatformGetAuthorInfoRequest(App.Info.id, authAppId));
        String respJson = HttpUtils.post(urlLocation, postContent);
        return JSON.parseObject(respJson, PlatFormGetAuthorizerInfoResult.class);
    }
    
    /**
     * 该API用于获取授权方的公众号的选项设置信息，如：地理位置上报，语音识别开关，多客服开关。<br>
     * 注意，获取各项选项设置信息，需要有授权方的授权，详见权限集说明。
     * @param authAppId 授权公众号appid
     * @param optionName 选项名称
     * @return
     */
    public PlatFormGetAuthorizerOptionResult getAuthorizerOption(
            String authAppId, String optionName) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("component_access_token", platFormTokenAccessor.getAccessToken());
        String urlLocation = getAuthorizerOptionUrl.replace(params);
        String postContent = JSON.toJSONString(new PlatformGetAuthorizerOptionRequest(App.Info.id, authAppId, optionName));
        String respJson = HttpUtils.post(urlLocation, postContent);
        return JSON.parseObject(respJson, PlatFormGetAuthorizerOptionResult.class);
    }
    
    /**
     * 该API用于设置授权方的公众号的选项信息，如：地理位置上报，语音识别开关，多客服开关。<br>
     * 注意，设置各项选项设置信息，需要有授权方的授权，详见权限集说明。
     * @param authAppId 授权公众号appid
     * @param optionName 选项名称
     * @param optionValue 设置的选项值
     * @return
     */
    public PlatFormSetAuthorizerOptionResult setAuthorizerOption(
            String authAppId, String optionName, String optionValue) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("component_access_token", platFormTokenAccessor.getAccessToken());
        String urlLocation = setAuthorizerOptionUrl.replace(params);
        String postContent = JSON.toJSONString(new PlatformSetAuthorizerOptionRequest(App.Info.id, authAppId, optionName, optionValue));
        String respJson = HttpUtils.post(urlLocation, postContent);
        return JSON.parseObject(respJson, PlatFormSetAuthorizerOptionResult.class);
    }
    
    private String getPlatFormManagerKey() {
        
        return "PlatFormManager-" + App.Info.id;
    }

}
