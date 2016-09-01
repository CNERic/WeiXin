package com.bj58.hrg.investment.wx.api;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.bj58.hrg.investment.common.HttpUtils;
import com.bj58.hrg.investment.common.StringTemplate;
import com.bj58.hrg.investment.wx.annotation.Autowired;
import com.bj58.hrg.investment.wx.annotation.Singleton;
import com.bj58.hrg.investment.wx.enums.MessageType;
import com.bj58.hrg.investment.wx.vo.request.MaterialDeleteRequest;
import com.bj58.hrg.investment.wx.vo.request.MaterialGetRequest;
import com.bj58.hrg.investment.wx.vo.result.BasicResult;
import com.bj58.hrg.investment.wx.vo.result.MaterialGetCountResult;
import com.bj58.hrg.investment.wx.vo.result.MaterialGetResult;
import com.bj58.hrg.investment.wx.vo.result.MaterialUploadResult;

/**
 * @class MaterialManager
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Singleton
public class MaterialManager {

    private static final StringTemplate postTempUrl = StringTemplate.compile(WeiXinURL.POST_TEMP);
    private static final StringTemplate getTempUrl = StringTemplate.compile(WeiXinURL.GET_TEMP);
    private static final StringTemplate postPermFileUrl = StringTemplate.compile(WeiXinURL.ADD_MATERIAL);
    private static final StringTemplate deletePermFileUrl = StringTemplate.compile(WeiXinURL.DEL_MATERIAL);
    private static final StringTemplate countPermFileUrl = StringTemplate.compile(WeiXinURL.COUNT_MATERIAL);
    private static final StringTemplate batchGetPermFileUrl = StringTemplate.compile(WeiXinURL.GET_MATERIALS);
    
    @Autowired
    private TokenAccessor tokenAccessor;
    
    /**
     * downloadFile
     * @param mediaId
     * @return 返回文件内容
     */
    public byte[] downloadTemp(String mediaId) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        params.put("mediaId", mediaId);
        String urlLocation = getTempUrl.replace(params);
        
        return HttpUtils.download(urlLocation);
    }
    
    /**
     * 文件上传，媒体文件在后台保存时间为3天，即3天后media_id失效。 
     * @param type
     * @param fileContent
     * @return 返回 media_id
    */
    public MaterialUploadResult uploadTemp(MessageType type, byte[] fileContent) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        params.put("type", type.toString());
        String urlLocation = postTempUrl.replace(params);
        
        String respJson = HttpUtils.post(urlLocation, fileContent);
        return JSON.parseObject(respJson, MaterialUploadResult.class);
    }
    
    /**
     * 永久文件上传
     * @param type
     * @param fileContent
     * @return 返回 media_id
    */
    public MaterialUploadResult uploadPermanent(MessageType type, byte[] fileContent) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        params.put("type", type.toString());
        String urlLocation = postPermFileUrl.replace(params);
        
        Map<String, byte[]> files = new HashMap<String, byte[]>();
        files.put("media", fileContent);
        String respJson = HttpUtils.post(urlLocation, null, files);
        return JSON.parseObject(respJson, MaterialUploadResult.class);
    }
    
    /**
     * 删除永久素材
     * @param mediaId
     * @return
     */
    public BasicResult deletePermanent(String mediaId) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        String urlLocation = deletePermFileUrl.replace(params);
        
        String reqJson = JSON.toJSONString(new MaterialDeleteRequest(mediaId));
        String respJson = HttpUtils.post(urlLocation, reqJson);
        return JSON.parseObject(respJson, BasicResult.class);
    }
    
    /**
     * 获取永久素材的数量
     * @return
     */
    public MaterialGetCountResult getPermanentCount() {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        String urlLocation = countPermFileUrl.replace(params);
        
        String respJson = HttpUtils.get(urlLocation);
        return JSON.parseObject(respJson, MaterialGetCountResult.class);
    }
    
    /**
     * 获取永久素材列表
     * @return
     */
    public MaterialGetResult getPermanent(MessageType type, int offset, int count) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        String urlLocation = batchGetPermFileUrl.replace(params);
        
        String reqJson = JSON.toJSONString(new MaterialGetRequest(type, offset, count));
        String respJson = HttpUtils.post(urlLocation, reqJson);
        return JSON.parseObject(respJson, MaterialGetResult.class);
    }
    
}
