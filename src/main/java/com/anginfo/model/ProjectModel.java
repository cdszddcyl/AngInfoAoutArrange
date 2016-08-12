package com.anginfo.model;

import java.io.Serializable;

/**
 * 
 * @author StoneCai
 * 2016年08月05日17:01:03
 * 添加
 * 项目数据容器
 *
 */
public class ProjectModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5136465077864486866L;
	
	private String projectName;
	private String mvnHome;
	private String svnPath;
	private String cmd;
	private String tomcatHome;
	private String svnHome;
	private String svnCheckPath;
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getMvnHome() {
		return mvnHome;
	}
	public void setMvnHome(String mvnHome) {
		this.mvnHome = mvnHome;
	}
	public String getSvnPath() {
		return svnPath;
	}
	public void setSvnPath(String svnPath) {
		this.svnPath = svnPath;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getTomcatHome() {
		return tomcatHome;
	}
	public void setTomcatHome(String tomcatHome) {
		this.tomcatHome = tomcatHome;
	}
	public String getSvnHome() {
		return svnHome;
	}
	public void setSvnHome(String svnHome) {
		this.svnHome = svnHome;
	}
	public String getSvnCheckPath() {
		return svnCheckPath;
	}
	public void setSvnCheckPath(String svnCheckPath) {
		this.svnCheckPath = svnCheckPath;
	}
	
	
}
