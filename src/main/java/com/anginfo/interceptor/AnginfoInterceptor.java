package com.anginfo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.anginfo.model.User;

/**
 * 
 * @author StoneCai
 * 2016年08月05日10:44:21
 * 添加
 * 拦截所有请求
 *
 */
public class AnginfoInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		//System.out.println(1);
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		//System.out.println(2);
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		request.setAttribute("rootPath", "/AngInfoAoutArrange");
		 String url=request.getServletPath() ;
		 if("/".equals(url)||"/r/index".equals(url)||"/login/user".equals(url)||url.endsWith(".css")||url.endsWith(".js")||url.endsWith(".jpg")||url.endsWith(".png")){
			 return true;
		 }
		 //校验用户是否存在
		User user=  (User) request.getSession().getAttribute("SESSION_USER");
		if(user==null){
			 request.setAttribute("msg", "用户已过期请重新登录！");
			 request.getRequestDispatcher("/").forward(request, response); 
			return false;
		}
		return true;
	}

}
