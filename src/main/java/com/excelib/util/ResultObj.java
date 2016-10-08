package com.excelib.util;

/**
 * 封装类：用以返回固定结构数据给客户端
 * 描述：
 * 1. Code: 服务端的状态码（以表明客户端对服务端业务逻辑调用的状态）
 * 2. message: 服务端处理业务逻辑处理结果信息描述。
 * 3. data: 需要返回给客户端的业务数据。
 * @author zhouze
 *
 */
public class ResultObj {
	
	/** 缺省值，服务端处理成功。*/
	public static final String CODE_OK = "200";
	/** 由于包含语法错误，当前请求无法被服务器理解。 */
	public static final String CODE_BAD_REQUEST = "400";
	/** 服务器已经理解请求，但是拒绝执行它（权限拒绝）。*/
	public static final String CODE_FORBIDDEN = "403";
	/** 服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理。*/
	public static final String CODE_SERVER_ERROR = "500";
	
	private String code = ResultObj.CODE_OK;
	private String message;
	private Object data;
	
	/** 构造器*/
	public ResultObj() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void setCode(String code) {
	    this.code = code;
	}
	public String getCode() {
	    return code;
	}
	public void setMessage(String message) {
	    this.message = message;
	}
	public String getMessage() {
	    return message;
	}
	public void setData(Object data) {
	    this.data = data;
	}
	public Object getData() {
	    return data;
	}
	
	

}
