package com.anginfo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.TextMessage;

import com.anginfo.model.ProjectModel;
import com.anginfo.utils.FileOperate;
import com.anginfo.utils.RunCourse;
import com.anginfo.utils.SystemUtils;
import com.anginfo.utils.WebsocketEndPoint;
import com.google.gson.Gson;

/**
 * 
 * @author StoneCai 2016年08月05日17:07:53 添加 项目的操作
 *
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {
	private String basePath = System.getProperty("evan.webapp") + "exec/";

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void projectSave(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute ProjectModel model) {
		// 窗机配置文件
		setting(model);
		// 创建文件svn
		svn(model.getProjectName(), model.getSvnPath(), model.getSvnHome(), model.getSvnCheckPath());
		// 创建maven命令
		maven(model.getProjectName(), model.getMvnHome(), model.getCmd(), model.getSvnPath());
		// 创建tomcat命令
		tomcat(model.getProjectName(), model.getTomcatHome(), model.getSvnPath());
	}

	/**
	 * Stone.Cai 2016年08月12日09:03:19 添加 setting 文件
	 */
	public void setting(ProjectModel model) {
		File file = createFile(model.getProjectName(), "setting.txt");
		FileOperate.writerFileByLine(file, new Gson().toJson(model));
	}
	/**
	 * Stone.Cai
	 * 2016年08月12日09:42:52
	 * 添加
	 * 删除项目
	 */
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public void deleteProject(HttpServletRequest request, HttpServletResponse response,String project){
		try {
			//删除指定项目
			FileOperate.deleteDir(basePath+project);
			resultSuccess(response, "操作成功", null);
		} catch (Exception e) {
			resultError(response, "操作失败", null);
		}
		
	}
	/**
	 * Stone.Cai
	 * 2016年08月12日14:01:45
	 * 添加
	 * 获取setting 文件列表
	 */
	@RequestMapping(value = "/set", method = RequestMethod.GET)
	public void getSetting(HttpServletRequest request, HttpServletResponse response,String project){
		String str= FileOperate.readFileByLine(basePath+project+"/setting.txt");
		resultSuccess(response, "获取成功", str);
	}

	/**
	 * Stone.Cai 2016年08月05日17:16:21 添加 创建文件
	 */
	private File createFile(String projectName, String fileName) {
		try {
			File dir = new File(basePath + projectName);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File(basePath + projectName + "/" + fileName);
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			RunCourse.runShell("chmod 777 " + file.getPath());
			return file;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Stone.Cai 2016年08月05日17:30:12 添加 创建svn 命令
	 */
	private void svn(String prjectName, String svnPath, String svnHome, String checkPath) {
		String fileName = "svn.sh";
		if (!SystemUtils.IS_OS_LINUX) {
			// 创建文件
			fileName = "svn.bat";
		}
		File file = createFile(prjectName, fileName);
		StringBuffer cmds = new StringBuffer("cd ");
		cmds.append(svnPath).append(";\n");
		cmds.append(svnHome).append(" co ").append(checkPath);
		FileOperate.writerFileByLine(file, cmds.toString());
	}

	/**
	 * Stone.Cai 2016年08月05日17:39:14 添加 创建mvn命令
	 */
	private void maven(String prjectName, String mvnHome, String cmd, String svnPath) {
		// 创建文件
		String fileName = "mvn.sh";
		if (!SystemUtils.IS_OS_LINUX) {
			fileName = "mvn.bat";
		}
		File file = createFile(prjectName, fileName);
		StringBuffer cmds = new StringBuffer("cd ");
		cmds.append(svnPath).append("/").append(prjectName).append(";\n");
		cmds.append(mvnHome).append("/bin/mvn ").append(cmd);
		FileOperate.writerFileByLine(file, cmds.toString());
	}

	/**
	 * Stone.Cai 2016年08月08日10:23:54 添加 tomcat
	 */
	public void tomcat(String prjectName, String tomcateHome, String svnPath) {
		String fileName = null;
		if (SystemUtils.IS_OS_LINUX) {
			fileName = "tomcate.sh";
		} else {
			fileName = "tomcate.bat";
		}
		// 创建文件
		File file = createFile(prjectName, fileName);
		StringBuffer cmds = new StringBuffer("cd ");
		cmds.append(tomcateHome).append("/bin;\n");
		if (SystemUtils.IS_OS_LINUX) {
			cmds.append("./shutdown.sh;\n");
		} else {
			cmds.append("shutdown.bat;\n");
		}
		cmds.append("cd ").append(tomcateHome).append("/webapps/;\n");
		cmds.append("rm -rf ").append(prjectName).append("*;\n");
		cmds.append("cd ").append(svnPath).append("/").append(prjectName).append("/target;\n");
		cmds.append("mv ").append(prjectName).append("*.war ").append(tomcateHome).append("/webapps/")
				.append(prjectName).append(".war;\n");
		cmds.append("cd ").append(tomcateHome).append("/bin;\n");
		if (SystemUtils.IS_OS_LINUX) {
			cmds.append("./startup.sh;\n");
		} else {
			cmds.append("startup.bat;\n");
		}
		FileOperate.writerFileByLine(file, cmds.toString());
	}

	/**
	 * Stone.Cai 2016年08月09日11:34:23 添加 获取所有项目
	 */
	@RequestMapping(value = "/findProject", method = RequestMethod.GET)
	public void findProject(HttpServletRequest request, HttpServletResponse response) {
		try {
			File file = new File(basePath);
			List<String> nameList = new ArrayList<String>();
			for (File nf : file.listFiles()) {
				nameList.add(nf.getName());
			}
			resultSuccess(response, "获取成功", nameList);
		} catch (Exception e) {
			resultError(response, "数据获取错误", null);
		}
	}

	/**
	 * Stone.Cai 2016年08月09日13:16:12 添加 执行程序
	 */
	@RequestMapping(value = "/exec", method = RequestMethod.GET)
	public void exec(HttpServletRequest request, HttpServletResponse response, String project) {

		// 开启线程
		new Thread(new ExecThread(project)).start();
		resultSuccess(response, "启动成功！", null);
	}

	class ExecThread implements Runnable {
		private String projectName;

		public ExecThread(String projectName) {
			this.projectName = projectName;
		}

		public void run() {
			String path = basePath + projectName + "/";
			WebsocketEndPoint
					.sendMessageToUsers(new TextMessage("{\"pname\":\"" + projectName + "\",\"msg\":\"执行开始</br>\"}"));
			// 执行maven打包
			try {
				String fixe = "sh";
				if (!SystemUtils.IS_OS_LINUX) {
					fixe = "bat";
				}
				// svn
				WebsocketEndPoint.sendMessageToUsers(
						new TextMessage("{\"pname\":\"" + projectName + "\",\"msg\":\"svn checkout </br>\"}"));
				_exe(path + "svn." + fixe);
				// maven
				WebsocketEndPoint.sendMessageToUsers(
						new TextMessage("{\"pname\":\"" + projectName + "\",\"msg\":\"mvn 执行打包中...</br>\"}"));
				_exe(path + "mvn." + fixe);
				// tomcat
				WebsocketEndPoint.sendMessageToUsers(
						new TextMessage("{\"pname\":\"" + projectName + "\",\"msg\":\"tomcat 部署中...</br>\"}"));
				_exe(path + "tomcate." + fixe);
				WebsocketEndPoint
						.sendMessageToUsers(new TextMessage("{\"pname\":\"" + projectName + "\",\"msg\":\"end\"}"));
			} catch (Exception e) {
				WebsocketEndPoint
						.sendMessageToUsers(new TextMessage("{\"pname\":\"" + projectName + "\",\"msg\":\"error\"}"));
			}

		}

		private void _exe(String path) throws Exception {
			List<String> list = RunCourse.runShell(path);
			for (String string : list) {
				WebsocketEndPoint.sendMessageToUsers(
						new TextMessage("{\"pname\":\"" + projectName + "\",\"msg\":\"" + string + "\"}"));
			}
		}
	}

}
