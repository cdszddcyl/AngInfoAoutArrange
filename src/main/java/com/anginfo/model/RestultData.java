package com.anginfo.model;

/**
 * 
 * @author StoneCai 2016年08月05日15:03:42 添加 返回数据值
 *
 */
public class RestultData {

	private int statusCode;
	private String message;
	private Object data;
	
	
	
	public RestultData(){}

	public RestultData(int statusCode, String message, Object data) {
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
