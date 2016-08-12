package com.anginfo.model;

import java.io.Serializable;

/**
 * 
 * @author StoneCai 2016年08月05日11:38:57 添加 用户信息
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5382846201948169465L;
	private String userName;
	private String pwd;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
