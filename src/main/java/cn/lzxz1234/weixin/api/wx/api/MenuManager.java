package cn.lzxz1234.weixin.api.wx.api;

import cn.lzxz1234.weixin.api.common.HttpUtils;
import cn.lzxz1234.weixin.api.common.StringTemplate;
import cn.lzxz1234.weixin.api.wx.vo.Button;
import cn.lzxz1234.weixin.api.wx.vo.request.MenuCreateRequest;
import cn.lzxz1234.weixin.api.wx.vo.result.BasicResult;
import cn.lzxz1234.weixin.api.wx.vo.result.MenuQueryResult;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @class MenuManager
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class MenuManager {

    private static StringTemplate createButtons = StringTemplate.compile(WeiXinURL.CREATE_BUTTONS);
    private static StringTemplate queryMenu = StringTemplate.compile(WeiXinURL.QUERY_MENU);
    private static StringTemplate deleteMenu = StringTemplate.compile(WeiXinURL.DELETE_MENU);
    
    private TokenAccessor tokenAccessor;

    public MenuManager(TokenAccessor tokenAccessor) {

        this.tokenAccessor = tokenAccessor;
    }
    
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

}
