package com.bj58.hrg.investment.common;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * @class HttpUtils
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class HttpUtils {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final int TIME_OUT = 3000;
    
    public static String read(HttpServletRequest req) throws IOException {
        
        InputStream is = req.getInputStream();
        try {
            String reqCharsetEncoding = req.getCharacterEncoding();
            if(reqCharsetEncoding == null)
                reqCharsetEncoding = "UTF-8";
            return IOUtils.toString(is, reqCharsetEncoding);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
    
    
    /**
     * @description 解析请求参数，单值请求参数 map.get() 直接返回值，多值参数返回数组
     * @param req
     * @return 
    */
    public static Map<String, Object> decodeParams(HttpServletRequest req) {
        
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String[]> originalParams = req.getParameterMap();
        
        Iterator<Map.Entry<String, String[]>> it = originalParams.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, String[]> entry = it.next();
            String key = entry.getKey();
            String[] value = entry.getValue();
            result.put(key, value.length == 0 ? "" : (value.length == 1 ? value[0] : value));
        }
        return result;
    }
    
    public static byte[] download(String urlLocation) {
        
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            URL url = new URL(urlLocation);
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(TIME_OUT);
            conn.connect();
            
            is = conn.getInputStream();
            return IOUtils.toByteArray(is);
        } catch (Exception e) {
            throw new RuntimeException("请求错误！", e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
    
    public static String get(String urlLocation) {
        
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            URL url = new URL(urlLocation);
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(TIME_OUT);
            conn.connect();
            
            is = conn.getInputStream();
            return IOUtils.toString(is, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("请求错误！", e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
    
    public static String post(String urlLocation, String content) {
        
        
        HttpURLConnection conn = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            URL url = new URL(urlLocation);
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(TIME_OUT);
            conn.connect();
            
            os = conn.getOutputStream();
            os.write(content.getBytes("UTF-8"));
            IOUtils.closeQuietly(os);
            
            is = conn.getInputStream();
            return IOUtils.toString(is, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("请求错误！", e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    public static String post(String urlLocation, byte[] fileContent) {
        
        Map<String, byte[]> files = new HashMap<String, byte[]>();
        files.put("file", fileContent);
        return post(urlLocation, null, files);
    }

    public static String post(String url, Map<String, String> params, Map<String, byte[]> files) {

        String BOUNDARY = UUID.randomUUID().toString();
        String MULTIPART_FROM_DATA = "multipart/form-data";

        HttpURLConnection conn = null;
        OutputStream os = null;
        InputStream is = null;
        try {
            URL uri = new URL(url);
            conn = (HttpURLConnection) uri.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
    
            os=new BufferedOutputStream(conn.getOutputStream());
            if(params != null && params.size() > 0)
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    os.write("--".getBytes(UTF8));
                    os.write(BOUNDARY.getBytes(UTF8));
                    os.write("\r\nContent-Disposition: form-data; name=\"".getBytes(UTF8));
                    os.write(entry.getKey().getBytes(UTF8));
                    os.write("\"\r\n".getBytes(UTF8));
                    os.write("Content-Type: text/plain; charset=UTF-8\r\n".getBytes(UTF8));
                    os.write("Content-Transfer-Encoding: 8bit\r\n\r\n".getBytes(UTF8));
                    os.write(entry.getValue().getBytes("UTF-8"));
                    os.write("\r\n".getBytes(UTF8));
                }
    
            if (files != null && files.size() > 0)
                for (Map.Entry<String, byte[]> file : files.entrySet()) {
                    os.write("--".getBytes(UTF8));
                    os.write(BOUNDARY.getBytes(UTF8));
                    
                    os.write("\r\nContent-Disposition: form-data; name=\"".getBytes(UTF8));
                    os.write(file.getKey().getBytes(UTF8));
                    os.write("\"; filename=\"".getBytes(UTF8));
                    os.write(file.getKey().getBytes(UTF8));
                    os.write("\"\r\n".getBytes(UTF8));
                    os.write("Content-Type: application/octet-stream; charset=UTF-8\r\n\r\n".getBytes(UTF8));
                    os.write(file.getValue());
                    os.write("\r\n".getBytes(UTF8));
                }
            os.write("--".getBytes(UTF8));
            os.write(BOUNDARY.getBytes(UTF8));
            os.write("--\r\n".getBytes(UTF8));
            
            IOUtils.closeQuietly(os);
            is = conn.getInputStream();
            return IOUtils.toString(is, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("请求错误！", e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

}
