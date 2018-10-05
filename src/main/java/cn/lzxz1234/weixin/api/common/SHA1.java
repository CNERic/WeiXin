package cn.lzxz1234.weixin.api.common;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @class SHA1
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class SHA1 {

    public static String getSHA1(String token, String timestamp, String nonce) {
        
        try {
            String[] array = new String[]{token, timestamp, nonce};
            Arrays.sort(array);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) 
                sb.append(array[i]);
            
            String str = sb.toString();
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();
    
            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) hexstr.append(0);
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (Exception e) {
            throw new RuntimeException("未知错误！", e);
        }
    }
    
}
