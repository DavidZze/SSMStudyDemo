package com.excelib.util;

import java.io.InputStream;
import java.util.Properties;

public class HttpUploadUtils {

    
    public static String docHost = null;
    
    
    static {
        Properties properties = new Properties();
        InputStream iStream = HttpUploadUtils.class.getClassLoader().getResourceAsStream("com/excelib/util/docHost.properties");
        
//        InputStream iStream = int.class.getResourceAsStream("/com/excelib/util/docHost.properties");
        System.out.println("iStream:" + iStream);
        System.out.println(int.class.getResource("/com/excelib/util/docHost.properties"));
        try {
            properties.load(iStream);
            docHost = properties.getProperty("docHost").trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public HttpUploadUtils() {
    }
    
    public static void test() {
        System.out.println("---- test():docHost: " + docHost);
    }
    

}
