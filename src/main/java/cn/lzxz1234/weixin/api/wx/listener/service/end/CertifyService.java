package cn.lzxz1234.weixin.api.wx.listener.service.end;

import cn.lzxz1234.weixin.api.common.SHA1;
import cn.lzxz1234.weixin.api.common.StringUtils;
import cn.lzxz1234.weixin.api.wx.dto.App;
import cn.lzxz1234.weixin.api.wx.dto.Context;
import cn.lzxz1234.weixin.api.wx.listener.Service;
import org.apache.log4j.Logger;

/**
 * @class CertifyService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class CertifyService implements Service {

    private static Logger log = Logger.getLogger(CertifyService.class);
    
    /*
     * 加密/校验流程如下：
     * 1. 将token、timestamp、nonce三个参数进行字典序排序
     * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     */
    @Override
    public String doService(Context context) throws Exception {

        String signature = context.getString("signature");
        String timestamp = context.getString("timestamp");
        String nonce = context.getString("nonce");
        String echostr = context.getString("echostr");
        if(StringUtils.isEmpty(signature) || StringUtils.isEmpty(timestamp) ||
                StringUtils.isEmpty(nonce) || StringUtils.isEmpty(echostr))
            return "微信接口，禁止访问！";
        String calcSignature = SHA1.getSHA1(App.Info.token, timestamp, nonce);
        log.info("对比签名：["+calcSignature+"]["+signature+"]");
        return calcSignature.equals(signature) ? echostr : null;
    }

}
