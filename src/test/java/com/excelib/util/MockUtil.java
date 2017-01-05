package com.excelib.util;


// import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

// import com.jayway.jsonpath.JsonPath;

/**
 * 单元测试工具类。
 * @author zhouze 2016-12-19
 *
 */
public class MockUtil {

    /** 缺省构造器*/
    public MockUtil() {
    }

    /**
     * 描述：
     * mock http post的请求。
     * @param mvc
     * @param uri
     * @param json
     * @return
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    public static String mockHttpPost(MockMvc mvc, String uri, String json) throws UnsupportedEncodingException, Exception {
        String rsString = mvc.perform(post(uri, json)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes()))
                .andReturn()
                .getResponse()
                .getContentAsString();
        
//        assertNotEquals("500", JsonPath.read(rsString, "$.code"));
        
        return rsString;
    }
    
    /**
     * 描述：
     * mock http post的请求。
     * @param mvc
     * @param uri
     * @param json
     * @return
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    public static String mockHttpPost(MockMvc mvc, String uri, String json, Map<String, Object> sessionMap) throws UnsupportedEncodingException, Exception {
        String rsString = mvc.perform(post(uri, json)
                .characterEncoding("UTF-8")
                .sessionAttrs(sessionMap)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes()))
                .andReturn()
                .getResponse()
                .getContentAsString();
        
//        assertNotEquals("500", JsonPath.read(rsString, "$.code"));
        
        return rsString;
    }
    
    /**
     * 描述：
     * mock http post的请求。
     * @param mvc
     * @param uri
     * @param json
     * @return
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    public static String mockHttpGet(MockMvc mvc, String uri, Map<String, Object> sessionMap) throws UnsupportedEncodingException, Exception {
        
    	
    	String rsString = mvc.perform(get(uri)
                .characterEncoding("UTF-8")
                .sessionAttrs(sessionMap)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        
//        assertNotEquals("500", JsonPath.read(rsString, "$.code"));
        
        return rsString; 
        
    }  
    
    /**
     * 描述：
     * mock http post的请求。
     * @param mvc
     * @param uri
     * @param json
     * @return
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    public static String mockHttpGet(MockMvc mvc, String uri) throws UnsupportedEncodingException, Exception {
        
    	
    	String rsString = mvc.perform(get(uri)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        
//        assertNotEquals("500", JsonPath.read(rsString, "$.code"));
        
        return rsString; 
        
    }  
   
    
    
}
