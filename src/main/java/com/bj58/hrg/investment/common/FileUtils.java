package com.bj58.hrg.investment.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static byte[] readToByteArray(String path) throws IOException {
        
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            return IOUtils.toByteArray(is);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
    
}
