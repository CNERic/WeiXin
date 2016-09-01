package com.bj58.hrg.investment.wx.api;

import static com.bj58.hrg.investment.common.StringTemplate.compile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.bj58.hrg.investment.common.HttpUtils;
import com.bj58.hrg.investment.common.StringTemplate;
import com.bj58.hrg.investment.wx.annotation.Autowired;
import com.bj58.hrg.investment.wx.annotation.Singleton;
import com.bj58.hrg.investment.wx.vo.Button;
import com.bj58.hrg.investment.wx.vo.request.MenuCreateRequest;
import com.bj58.hrg.investment.wx.vo.result.BasicResult;
import com.bj58.hrg.investment.wx.vo.result.MenuQueryResult;

/**
 * @class MenuManager
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Singleton
public class MenuManager {

    private static StringTemplate createButtons = compile(WeiXinURL.CREATE_BUTTONS);
    private static StringTemplate queryMenu = compile(WeiXinURL.QUERY_MENU);
    private static StringTemplate deleteMenu = compile(WeiXinURL.DELETE_MENU);
    
    @Autowired
    private TokenAccessor tokenAccessor;
    @Autowired
    private PlatFormManager platFormManager;
    
    /**
     * @description 自定义菜单创建接口
     * @param button
     * @return 
     */
    public BasicResult createButtons(List<Button> button) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        String urlLocation = createButtons.replace(params);
        String reqString = JSON.toJSONString(new MenuCreateRequest(button));
        String respString = HttpUtils.post(urlLocation, reqString);
        return JSON.parseObject(respString, BasicResult.class);
    }
    
    /**
     * @description 自定义菜单查询接口
     * @return 
     */
    public MenuQueryResult queryMenu() {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        String urlLocation = queryMenu.replace(params);
        String respString = HttpUtils.get(urlLocation);
        return JSON.parseObject(respString, MenuQueryResult.class);
    }
    
    /**
     * @description 自定义菜单删除接口
     * @return 
     */
    public BasicResult deleteMenu() {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        String urlLocation = deleteMenu.replace(params);
        String respString = HttpUtils.get(urlLocation);
        return JSON.parseObject(respString, BasicResult.class);
    }
    

    /**
     * @description 自定义菜单创建接口
     * @param button
     * @return 
     */
    public BasicResult createButtons(String authAppId, String refreshToken, 
            List<Button> button) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", platFormManager.getAuthAccessToken(authAppId, refreshToken));
        String urlLocation = createButtons.replace(params);
        String reqString = JSON.toJSONString(new MenuCreateRequest(button));
        String respString = HttpUtils.post(urlLocation, reqString);
        return JSON.parseObject(respString, BasicResult.class);
    }
    
    /**
     * @description 自定义菜单查询接口
     * @return 
     */
    public MenuQueryResult queryMenu(String authAppId, String refreshToken) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", platFormManager.getAuthAccessToken(authAppId, refreshToken));
        String urlLocation = queryMenu.replace(params);
        String respString = HttpUtils.get(urlLocation);
        return JSON.parseObject(respString, MenuQueryResult.class);
    }
    
    /**
     * @description 自定义菜单删除接口
     * @return 
     */
    public BasicResult deleteMenu(String authAppId, String refreshToken) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", platFormManager.getAuthAccessToken(authAppId, refreshToken));
        String urlLocation = deleteMenu.replace(params);
        String respString = HttpUtils.get(urlLocation);
        return JSON.parseObject(respString, BasicResult.class);
    }
    
}
