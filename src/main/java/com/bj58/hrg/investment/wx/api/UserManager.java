package com.bj58.hrg.investment.wx.api;

import static com.bj58.hrg.investment.common.StringTemplate.compile;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.bj58.hrg.investment.common.HttpUtils;
import com.bj58.hrg.investment.common.StringTemplate;
import com.bj58.hrg.investment.wx.annotation.Autowired;
import com.bj58.hrg.investment.wx.annotation.Service;
import com.bj58.hrg.investment.wx.vo.request.UserRemarkRequest;
import com.bj58.hrg.investment.wx.vo.result.BasicResult;
import com.bj58.hrg.investment.wx.vo.result.FollowerQueryResult;
import com.bj58.hrg.investment.wx.vo.result.UserInfoQueryResult;

/**
 * @class UserManager
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Service
public class UserManager {

    @Autowired
    private TokenAccessor tokenAccessor;
    private static final StringTemplate remarkUserUrl = compile(WeiXinURL.REMARK_USER);
    private static final StringTemplate queryUserInfoUrl = compile(WeiXinURL.QUERY_USER_INFO);
    private static final StringTemplate queryFollowerUrl = compile(WeiXinURL.QUERY_FOLLOWER);
    
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
