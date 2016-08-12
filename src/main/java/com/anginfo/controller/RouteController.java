package com.anginfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author StoneCai
 * 2016年08月05日10:00:53
 * 添加
 * index页面的数据请求
 *
 */
@Controller
@RequestMapping("/r")
public class RouteController {
	
	@RequestMapping("/{url}")
	public String route(@PathVariable String url){
		return url;
	}
}
