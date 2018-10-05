package cn.lzxz1234.weixin.api.wx.api;

import cn.lzxz1234.weixin.api.common.HttpUtils;
import cn.lzxz1234.weixin.api.common.StringTemplate;
import cn.lzxz1234.weixin.api.wx.vo.request.GroupCreateRequest;
import cn.lzxz1234.weixin.api.wx.vo.request.GroupUpdateRequest;
import cn.lzxz1234.weixin.api.wx.vo.request.GroupUserQueryRequest;
import cn.lzxz1234.weixin.api.wx.vo.request.GroupUserUpdateRequest;
import cn.lzxz1234.weixin.api.wx.vo.result.BasicResult;
import cn.lzxz1234.weixin.api.wx.vo.result.GroupCreateResult;
import cn.lzxz1234.weixin.api.wx.vo.result.GroupQueryResult;
import cn.lzxz1234.weixin.api.wx.vo.result.UserGroupQueryResult;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @class UserManager
 * @author lzxz1234
 * @version v1.0
 */
public class GroupManager {

    private static final StringTemplate createGroupUrl = StringTemplate.compile(WeiXinURL.CREATE_GROUP);
    private static final StringTemplate queryGroupsUrl = StringTemplate.compile(WeiXinURL.QUERY_GROUPS);
    private static final StringTemplate queryUserGroupUrl = StringTemplate.compile(WeiXinURL.QUERY_USER_GROUP);
    private static final StringTemplate modifyGroupUrl = StringTemplate.compile(WeiXinURL.MODIFY_GROUP);
    private static final StringTemplate modifyUserGroupUrl = StringTemplate.compile(WeiXinURL.MODIFY_USER_GROUP);
    
    private TokenAccessor tokenAccessor;

    public GroupManager(TokenAccessor tokenAccessor) {
        this.tokenAccessor = tokenAccessor;
    }
    
    /**
     * 创建分组<br>
     * 一个公众账号，最多支持创建100个分组
     * @param groupName
     * @return 
    */
    public GroupCreateResult createGroup(String groupName) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        String urlLocation = createGroupUrl.replace(params);
        String postString = JSON.toJSONString(new GroupCreateRequest(groupName));
        return postAndDecode(urlLocation, postString, GroupCreateResult.class);
    }
    
    /**
     * 查询所有分组
     * @return 
    */
    public GroupQueryResult queryGroups() {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        String urlLocation = queryGroupsUrl.replace(params);
        String respString = HttpUtils.get(urlLocation);
        return JSON.parseObject(respString, GroupQueryResult.class);
    }
    
    /**
     * 通过用户的OpenID查询其所在的GroupID
     * @param userId
     * @return 
    */
    public UserGroupQueryResult queryUserGroup(String userId) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        String urlLocation = queryUserGroupUrl.replace(params);
        String postString = JSON.toJSONString(new GroupUserQueryRequest(userId));
        return postAndDecode(urlLocation, postString, UserGroupQueryResult.class);
    }
    
    /**
     * 修改分组名
     * @param groupId 分组id，由微信分配
     * @param groupName 分组名字（30个字符以内）
     * @return 
    */
    public BasicResult modifyGroup(String groupId, String groupName) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        String urlLocation = modifyGroupUrl.replace(params);
        String postString = JSON.toJSONString(new GroupUpdateRequest(groupId, groupName));
        return postAndDecode(urlLocation, postString, BasicResult.class);
    }
    
    /**
     * 移动用户分组
     * @param userId 用户唯一标识符
     * @param toGroupId 分组id
     * @return 
    */
    public BasicResult modfiyUserGroup(String userId, String toGroupId) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        String urlLocation = modifyUserGroupUrl.replace(params);
        String postString = JSON.toJSONString(new GroupUserUpdateRequest(userId, toGroupId));
        return postAndDecode(urlLocation, postString, BasicResult.class);
    }
    
    private <T> T postAndDecode(String urlLocation, String postString, Class<T> target) {
        
        String respString = HttpUtils.post(urlLocation, postString);
        return JSON.parseObject(respString, target);
    }
    
}
