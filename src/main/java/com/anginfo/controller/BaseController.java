package com.anginfo.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import com.anginfo.model.RestultData;
import com.google.gson.Gson;

/**
 * 
 * @author StoneCai
 * 2016年08月05日15:00:01
 * 添加
 * 通用方法在这里呢
 *
 */
public class BaseController {
	
    @Autowired  
    private  HttpServletRequest request; 
    /**
     * Stone.Cai
     * 2016年08月05日15:15:11
     * 添加
     * 写出数据信息
     * @param data
     */
    public void writeJson(RestultData data,HttpServletResponse response){
    	try {
    		Gson gson=new Gson();
        	String json= gson.toJson(data);
        	response.setHeader("Content-type", "application/json; charset=utf-8"); 
        	response.setCharacterEncoding("UTF-8"); 
        	response.getWriter().write(json);
		} catch (Exception e) {
		}
    }
    /**
     * Stone.Cai
     * 2016年08月05日15:15:33
     * 添加
     * 成功的json
     */
    public void resultSuccess(HttpServletResponse response,String msg,Object data){
    	writeJson(new RestultData(200, msg, data),response);
    }
    /**
     * Stone.Cai
     * 2016年08月05日15:15:33
     * 添加
     * 成功的json
     */
    public void resultError(HttpServletResponse response,String msg,Object data){
    	writeJson(new RestultData(500, msg, data),response);
    }
}
