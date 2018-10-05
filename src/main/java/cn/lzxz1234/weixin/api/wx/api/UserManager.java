package cn.lzxz1234.weixin.api.wx.api;

import cn.lzxz1234.weixin.api.common.HttpUtils;
import cn.lzxz1234.weixin.api.common.StringTemplate;
import cn.lzxz1234.weixin.api.wx.vo.request.UserRemarkRequest;
import cn.lzxz1234.weixin.api.wx.vo.result.BasicResult;
import cn.lzxz1234.weixin.api.wx.vo.result.FollowerQueryResult;
import cn.lzxz1234.weixin.api.wx.vo.result.UserInfoQueryResult;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @class UserManager
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class UserManager {

    private TokenAccessor tokenAccessor;
    private static final StringTemplate remarkUserUrl = StringTemplate.compile(WeiXinURL.REMARK_USER);
    private static final StringTemplate queryUserInfoUrl = StringTemplate.compile(WeiXinURL.QUERY_USER_INFO);
    private static final StringTemplate queryFollowerUrl = StringTemplate.compile(WeiXinURL.QUERY_FOLLOWER);

    public UserManager(TokenAccessor tokenAccessor) {

        this.tokenAccessor = tokenAccessor;
    }

    /**
     * @description 修改用户备注名
     * @param userId
     * @param remark
     * @return 
    */
    public BasicResult remarkUser(String userId, String remark) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        String urlLocation = remarkUserUrl.replace(params);
        String reqString = JSON.toJSONString(new UserRemarkRequest(userId, remark));
        String respString = HttpUtils.post(urlLocation, reqString);
        return JSON.parseObject(respString, BasicResult.class);
    }
    
    /**
     * @description 获取用户基本信息（包括UnionID机制）
     * @param userId
     * @return 
    */
    public UserInfoQueryResult queryUserInfo(String userId) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        params.put("openid", userId);
        String urlLocation = queryUserInfoUrl.replace(params);
        String respString = HttpUtils.get(urlLocation);
        return JSON.parseObject(respString, UserInfoQueryResult.class);
    }
    
    /**
     * @description 获取关注者列表
     * @param nextOpenId
     * @return 
    */
    public FollowerQueryResult queryFollowers(String nextOpenId) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        params.put("next_openid", nextOpenId == null ? "" : nextOpenId);
        String urlLocation = queryFollowerUrl.replace(params);
        String respString = HttpUtils.get(urlLocation);
        return JSON.parseObject(respString, FollowerQueryResult.class);
    }
    
}
