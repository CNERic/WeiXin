package cn.lzxz1234.weixin.api.common;

import java.io.UnsupportedEncodingException;

/**
 * @class StringUtils
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class StringUtils {

    public static boolean isEmpty(String src) {
        
        return src == null || src.length() == 0;
    }

    public static byte[] getBytesUtf8(String str) {
        
        if(str == null) return null;
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String newStringUtf8(byte[] bytes) {
        try {
            return bytes == null ? null : new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
}
