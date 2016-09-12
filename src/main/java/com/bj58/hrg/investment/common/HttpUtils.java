package com.bj58.hrg.investment.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @class HttpUtils
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class HttpUtils {

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

        try {
            MultipartUtility req = new MultipartUtility(url, "UTF-8");
            if(params != null)
                for(Map.Entry<String, String> each : params.entrySet()) 
                    req.addFormField(each.getKey(), each.getValue());
            if(files != null)
                for(Map.Entry<String, byte[]> each : files.entrySet())
                    req.addFilePart(each.getKey(), each.getValue());
            return req.finish();
        } catch (Exception e) {
            throw new RuntimeException("请求失败", e);
        }
    }

    public static class MultipartUtility {
        
        private final String boundary;
        private static final String LINE_FEED = "\r\n";
        private HttpURLConnection httpConn;
        private String charset;
        private OutputStream outputStream;
        private PrintWriter writer;
     
        public MultipartUtility(String requestURL, String charset) throws Exception {
            
            this.charset = charset;
            boundary = "===" + System.currentTimeMillis() + "===";
             
            URL url = new URL(requestURL);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setUseCaches(false);
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            httpConn.setRequestProperty("User-Agent", "CodeJava Agent");
            outputStream = httpConn.getOutputStream();
            writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);
        }
     
        public void addFormField(String name, String value) {
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"" + name + "\"").append(LINE_FEED);
            writer.append("Content-Type: text/plain; charset=" + charset).append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.append(value).append(LINE_FEED);
            writer.flush();
        }
     
        public void addFilePart(String fieldName, byte[] uploadFile) throws IOException {
            
            String contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(uploadFile));
            String suffix = contentType.contains("/") ? contentType.split("/")[1] : contentType;
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"uploadfile.").append(suffix).append("\"").append(LINE_FEED);
            writer.append("Content-Type: " + contentType).append(LINE_FEED);
            writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.flush();
     
            outputStream.write(uploadFile);
            outputStream.flush();
             
            writer.append(LINE_FEED);
            writer.flush();     
        }
     
        public void addHeaderField(String name, String value) {
            writer.append(name + ": " + value).append(LINE_FEED);
            writer.flush();
        }
         
        public String finish() throws IOException {
     
            writer.append(LINE_FEED).flush();
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();
     
            int status = httpConn.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                String result = IOUtils.toString(httpConn.getInputStream(), "UTF-8");
                httpConn.disconnect();
                return result;
            } else {
                throw new IOException("Server returned non-OK status: " + status);
            }
     
        }
    }

}
