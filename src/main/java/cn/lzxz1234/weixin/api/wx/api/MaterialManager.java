package cn.lzxz1234.weixin.api.wx.api;

import cn.lzxz1234.weixin.api.common.HttpUtils;
import cn.lzxz1234.weixin.api.common.StringTemplate;
import cn.lzxz1234.weixin.api.wx.enums.MessageType;
import cn.lzxz1234.weixin.api.wx.vo.request.MaterialDeleteRequest;
import cn.lzxz1234.weixin.api.wx.vo.request.MaterialGetRequest;
import cn.lzxz1234.weixin.api.wx.vo.result.BasicResult;
import cn.lzxz1234.weixin.api.wx.vo.result.MaterialGetCountResult;
import cn.lzxz1234.weixin.api.wx.vo.result.MaterialGetResult;
import cn.lzxz1234.weixin.api.wx.vo.result.MaterialUploadResult;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @class MaterialManager
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class MaterialManager {

    private static final StringTemplate postTempUrl = StringTemplate.compile(WeiXinURL.POST_TEMP);
    private static final StringTemplate getTempUrl = StringTemplate.compile(WeiXinURL.GET_TEMP);
    private static final StringTemplate postPermFileUrl = StringTemplate.compile(WeiXinURL.ADD_MATERIAL);
    private static final StringTemplate deletePermFileUrl = StringTemplate.compile(WeiXinURL.DEL_MATERIAL);
    private static final StringTemplate countPermFileUrl = StringTemplate.compile(WeiXinURL.COUNT_MATERIAL);
    private static final StringTemplate batchGetPermFileUrl = StringTemplate.compile(WeiXinURL.GET_MATERIALS);
    
    private TokenAccessor tokenAccessor;

    public MaterialManager(TokenAccessor tokenAccessor) {

        this.tokenAccessor = tokenAccessor;
    }

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
