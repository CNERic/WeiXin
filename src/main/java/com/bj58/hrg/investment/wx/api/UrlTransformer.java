package com.bj58.hrg.investment.wx.api;

import static com.bj58.hrg.investment.common.StringTemplate.compile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.bj58.hrg.investment.common.HttpUtils;
import com.bj58.hrg.investment.common.StringTemplate;
import com.bj58.hrg.investment.wx.annotation.Autowired;
import com.bj58.hrg.investment.wx.annotation.Singleton;

@Singleton
public class UrlTransformer {

    private static final StringTemplate urlTransformUrl = compile(WeiXinURL.SHORT_URL);
    @Autowired
    private TokenAccessor tokenAccessor;
    
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
