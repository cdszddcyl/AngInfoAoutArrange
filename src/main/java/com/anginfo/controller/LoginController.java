package com.anginfo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anginfo.model.User;

/**
 * 
 * @author StoneCai
 * 2016年08月05日11:43:05
 * 添加
 * 登录信息
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@RequestMapping(value="/user", method = RequestMethod.POST)
	public void route(HttpServletRequest request,HttpServletResponse response,@ModelAttribute User user){
		
		if("admin".equals(user.getUserName())&&"111111".equals(user.getPwd())){
			request.getSession().setAttribute("SESSION_USER", user);
			this.resultSuccess(response,"操作成功","r/main");
		}else{
			this.resultError(response,"失败", "");
		}
	}

}
