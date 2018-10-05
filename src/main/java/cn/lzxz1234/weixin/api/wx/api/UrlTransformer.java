package cn.lzxz1234.weixin.api.wx.api;

import cn.lzxz1234.weixin.api.common.HttpUtils;
import cn.lzxz1234.weixin.api.common.StringTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static cn.lzxz1234.weixin.api.common.StringTemplate.compile;

public class UrlTransformer {

    private static final StringTemplate urlTransformUrl = compile(WeiXinURL.SHORT_URL);
    private TokenAccessor tokenAccessor;

    public UrlTransformer(TokenAccessor tokenAccessor) {

        this.tokenAccessor = tokenAccessor;
    }
    
    /**
     * @param longUrl 需要转换的长链接，支持http://、https://、weixin://wxpay 格式的url 
     * @return 长地址转短地址
     */
    public String long2short(String longUrl) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", tokenAccessor.getAccessToken());
        String urlLocation = urlTransformUrl.replace(params);
        try {
            return HttpUtils.post(urlLocation, "action=long2short&long_url="+URLEncoder.encode(longUrl, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("No Avaliable");
        }
    }
}
