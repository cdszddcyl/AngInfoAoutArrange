<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String rootPath=request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="js/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery3/jquery3.js"></script>
<script type="text/javascript" src="js/bootstrap3/js/bootstrap.min.js"></script>
<title>欢迎您登录</title>

<script type="text/javascript">
var wid=0;
function watingOpen(){
	$("#wating").fadeIn();
	var  IntervalName= setInterval(function(){
		wid=wid+10;
		if(wid>90){
			clearInterval(IntervalName);
			return;
		}
		$("#jcline").css("width", wid+"%");
	}, 1000);
}
function watingClose(){
	wid=100;
	$("#jcline").fadeOut();
	setTimeout(function(){
		$("#wating").css("display","none");
	}, 2000)
}
function myAlertOpen(){
	$("#myAlert").fadeIn();
	setTimeout(function(){
		$("#myAlert").fadeOut();
	}, 3000)
}

function searchData(obj){
	var view='<div class=" panel-default">';
	view+='<div class="panel-heading">';
	view+='<h4 class="panel-title">';
	view+='	<a data-toggle="collapse" data-parent="#accordion" href="#collapse'+obj.projectName+'">'+obj.projectName+'</a>';
	view+='<a id="rbtn'+obj.projectName+'" href="javascript:execSec(\''+obj.projectName+'\')" style="right: 30px;position: fixed;color: #1A94E6" >执行</a>';
	view+='<a id="dbtn'+obj.projectName+'" href="javascript:execDel(\''+obj.projectName+'\')" style="right: 70px;position: fixed;color: #1A94E6" >删除</a>';
	view+='<a id="ebtn'+obj.projectName+'" href="javascript:execEdit(\''+obj.projectName+'\')" style="right: 110px;position: fixed;color: #1A94E6" >编辑</a>';
	view+='	</h4>';
	view+='</div>';
	view+='<div id="collapse'+obj.projectName+'" class="panel-collapse collapse">';
	view+='	<div class="panel-body">';
	view+='		<div class="container">';
	view+='	<div class="row">';
	view+='		<div class="col-md-15"';
	view+='			style="background-color: #dedef8; height: 400px;">';
	view+='			<div style="width: 100%; height: 20px; line-height: 20px;">日志输出</div>';
	view+='			<div';
	view+='				id="console'+obj.projectName+'" style="width: 100%; background-color: #000; height: 360px; color: #fff; overflow: auto">';
	view+='				设备已就绪</br>';
	view+='			</div>';
	view+='		</div>';
	view+='	</div>';
	view+='</div>';
	view+='</div>';
	view+='</div>';
	view+='</div>';
	$("body").append(view);
}
$(function(){
	//查询项目
	resPr();
});

function resPr(){
	$.ajax({
		type : "GET",
		url : "project/findProject",
		data : "",
		success : function(msg) {
			if(msg.statusCode!=200){
				alert(msg.message);
				return;
			}
			$.each(msg.data,function(index,item){
				searchData({projectName:item});
			});
		}
	});
}
var isexec=false;
function execSec(por){
	if(isexec){
		alert("有进程正在执行了");
		return;
	}
	isexec=true;
	$("#console"+por).append("</br>开始执行</br>");
	$("#rbtn"+por).html("执行中...");
	$.ajax({
		type : "GET",
		url : "project/exec",
		data : "project="+por,
		success : function(msg) {
			//console.log(msg);
		}
	});
}
function execDel(por){
	$.ajax({
		type : "GET",
		url : "project/del",
		data : "project="+por,
		success : function(msg) {
			myAlertOpen();
			setTimeout(function(){
				window.location.reload();
			}, 2000);
		}
	});
}

function execEdit(pro){
	//获取数据
	$.ajax({
		type : "GET",
		url : "project/set",
		data : "project="+pro,
		success : function(msg) {
			if(msg.statusCode==200){
				var model= eval("("+msg.data+")");
				console.log(model);
				$("#projectNamee").val(model.projectName);
				$("#mvnHomee").val(model.mvnHome);
				$("#svne").val(model.svnPath);
				$("#cmde").val(model.cmd);
				$("#tomcatHomee").val(model.tomcatHome);
				$("#svnHomee").val(model.svnHome);
				$("#svncoe").val(model.svnCheckPath);
			}
		}
	});
	//打开界面
	$('#editModal').modal('show');
}
</script>
</head>
<body>
	<nav class="navbar navbar-primary" style="margin-bottom:0px;"
		role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" style="color: #ffffff" href="#">青岛埃米信息技术有限公司</a>
		<a data-toggle="modal" data-target="#myModal"
			style="position: absolute; right: 70px; color: #ffffff; top: 15px;">欢迎【admin】登录</a>
	</div>
	<div style="background-color: #00b3ee;"
		class="collapse navbar-collapse" id="example-navbar-collapse"></div>
	</nav>
	<div id="wating" style="margin-bottom:0px;display: none;" class="progress progress-striped active">
		   <div class="progress-bar progress-bar-info" role="progressbar" 
		      aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"  id="jcline"
		      style="width: 0%;">
		      <span class="sr-only"></span>
		   </div>
		</div>
	<div id="myAlert" style="margin-bottom:0px;display: none;" class="alert alert-success">
	   <a href="#" class="close" data-dismiss="alert">&times;</a>
	   <strong>消息！</strong>操作成功！。
	</div>
	<div class="panel-group" style="margin-bottom:10px;" id="accordion">
	
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseTwo"> 添加一个呢 </a>
						
				</h4>
			</div>
			<div id="collapseTwo" class="panel-collapse collapse">
				<div class="panel-body">
					<div class="input-group">
						<span class="input-group-addon">projectname</span> <input
							id="projectName" type="text" class="form-control"
							placeholder="项目名称(一定要和项目名一致)">
					</div>
					<div class="input-group" style="margin-top: 10px;">
						<span class="input-group-addon">mvn</span> <input id="mvnHome"
							type="text" class="form-control" placeholder="maven-home">
					</div>
					<div class="input-group" style="margin-top: 10px;">
						<span class="input-group-addon">svn</span> <input id="svn"
							type="text" class="form-control" placeholder="项目svn根目录不包含项目的主路径">
					</div>
					<div class="input-group" style="margin-top: 10px;">
						<span class="input-group-addon">cmd</span> <input id="cmd"
							type="text" class="form-control" placeholder="打包命令">
					</div>
					<div class="input-group" style="margin-top: 10px;">
						<span class="input-group-addon">tomcat</span> <input
							id="tomcatHome" type="text" class="form-control"
							placeholder="tomcat-home">
					</div>
					<div class="input-group" style="margin-top: 10px;">
						<span class="input-group-addon">svnHome</span> <input
							id="svnHome" type="text" class="form-control"
							placeholder="svn-home 例如/usr/bin/svn ">
					</div>
					<div class="input-group" style="margin-top: 10px;">
						<span class="input-group-addon">svnCO</span> <input
							id="svnco" type="text" class="form-control"
							placeholder="svn-checkout 地址 例如svn://caids@139.129.35.40/myt 但这个必须已经迁出过 ">
					</div>
					<button onclick="savePrject(1)" type="button"
						style="margin-top: 10px;" class="btn btn-primary btn-lg btn-block">
						提交</button>
				</div>
			</div>
		</div>

	</div>
	
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">
               修改
            </h4>
         </div>
         <div class="modal-body">
            	<div class="input-group">
						<span class="input-group-addon">projectname</span> <input
							id="projectNamee" type="text" disabled class="form-control"
							placeholder="项目名称(一定要和项目名一致)">
					</div>
					<div class="input-group" style="margin-top: 10px;">
						<span class="input-group-addon">mvn</span> <input id="mvnHomee"
							type="text" class="form-control" placeholder="maven-home">
					</div>
					<div class="input-group" style="margin-top: 10px;">
						<span class="input-group-addon">svn</span> <input id="svne"
							type="text" class="form-control" placeholder="项目svn根目录不包含项目的主路径">
					</div>
					<div class="input-group" style="margin-top: 10px;">
						<span class="input-group-addon">cmd</span> <input id="cmde"
							type="text" class="form-control" placeholder="打包命令">
					</div>
					<div class="input-group" style="margin-top: 10px;">
						<span class="input-group-addon">tomcat</span> <input
							id="tomcatHomee" type="text" class="form-control"
							placeholder="tomcat-home">
					</div>
					<div class="input-group" style="margin-top: 10px;">
						<span class="input-group-addon">svnHome</span> <input
							id="svnHomee" type="text" class="form-control"
							placeholder="svn-home 例如/usr/bin/svn ">
					</div>
					<div class="input-group" style="margin-top: 10px;">
						<span class="input-group-addon">svnCO</span> <input
							id="svncoe" type="text" class="form-control"
							placeholder="svn-checkout 地址 例如svn://caids@139.129.35.40/myt 但这个必须已经迁出过 ">
					</div>
         </div>
         <div class="modal-footer">
            <button onclick="updatas()" type="button" class="btn btn-primary btn-lg btn-block">
               提交更改
            </button>
         </div>
      </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	
	
	
	<script type="text/javascript">
		function savePrject(vb) {
			var projectName ;
			var mvnHome ;
			var svnPath;
			var cmd ;
			var tomcatHome ;
			var svnHome;
			var svnco;
			if(vb==1){
				 projectName = $("#projectName").val();
			 	 mvnHome = $("#mvnHome").val();
				 svnPath = $("#svn").val();
				 cmd = $("#cmd").val();
				 tomcatHome = $("#tomcatHome").val();
				 svnHome=$("#svnHome").val();
				 svnco=$("#svnco").val();
			}else{
				projectName = $("#projectNamee").val();
			 	 mvnHome = $("#mvnHomee").val();
				 svnPath = $("#svne").val();
				 cmd = $("#cmde").val();
				 tomcatHome = $("#tomcatHomee").val();
				 svnHome=$("#svnHomee").val();
				 svnco=$("#svncoe").val();
			}
			
			watingOpen();
			$.ajax({
				type : "POST",
				url : "project/save",
				data : "projectName=" + projectName + "&mvnHome=" + mvnHome
						+ "&svnPath=" + svnPath + "&cmd=" + cmd
						+ "&tomcatHome=" + tomcatHome+"&svnHome="+svnHome+"&svnCheckPath="+svnco,
				success : function(msg) {
					watingClose();
					myAlertOpen();
					setTimeout(function(){
						window.location.reload();
					}, 3000);
				}
			});
		}
		
		var websocket;
		function connect() {
			if ('WebSocket' in window) {
				websocket = new WebSocket("ws://<%=rootPath%>websocket");
			} else if ('MozWebSocket' in window) {
				websocket = new MozWebSocket("ws://<%=rootPath%>webSocketServer");
			} else {
				websocket = new SockJS("http://<%=rootPath%>sockjs/websocket");
			}
			websocket.onopen = function(evnt) {
				console.log("客户端已经打通服务器。");
			};
			websocket.onmessage = function(evnt) {
				var data= eval("("+evnt.data+")");
						if(data.msg=="error"){
							isexec=false;
							$("#console"+data.pname).append("执行过程遇到错误！");
							return;
						}
						if(data.msg=="end"){
							isexec=false;
							$("#console"+data.pname).append("</br>执行结束");
							$("#rbtn"+data.pname).html("执行");
							return;
						}
						$("#console"+data.pname).append(data.msg);
			};
			websocket.onerror = function(evnt) {
				alert("服务器已经断开连接！");
			};
			websocket.onclose = function(evnt) {
				alert("服务器已经断开连接！");
			}
		}
		$(function(){
			connect();
			//savePrject(2);
			});
		function updatas(){
			$('#editModal').modal('hide');
			savePrject(2);
		}
	</script>
</body>
</html>