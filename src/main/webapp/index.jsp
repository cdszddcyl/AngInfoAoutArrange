<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎使用</title>
<base href="<%=basePath %>" />
<link href="js/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery3/jquery3.js"></script>
<script type="text/javascript" src="js/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function() {
	$("#myModal").on("hide.bs.modal",function(){
		openWating();
		setTimeout("execLogin()", 3000);
	});
});
var IntervalName;

//打开进图条
function openWating(){
	$("#wating").fadeIn();
	var wid=0;
	IntervalName= setInterval(function(){
		wid=wid+10;
		if(wid>90){
			clearInterval(IntervalName);
			return;
		}
		$("#jcline").css("width", wid+"%");
	}, 1000);
}
//执行登录
function execLogin(){
	var userName= $("#userName").val();
	var pwd=$("#pwd").val();
	$.ajax({
		   type: "POST",
		   url: "login/user",
		   data: "userName="+userName+"&pwd="+pwd,
		   success: function(msg){
			   clearInterval(IntervalName);
		     if(msg.statusCode!=200){
		    	 $("#wating").fadeOut();
		    	 $("#myAlert").fadeIn();
		    	 $("#jcline").css("width", 0+"%");
		    	 setTimeout(function(){
		    		 $("#myAlert").fadeOut();
		    	 }, 5000);
		     }else{
		    	 $("#jcline").css("width", 100+"%");
		    	 setTimeout(function(){
		    		 window.location.href=msg.data;
		    	 }, 1000);
		     }
		   }
		});
}
</script>
</head>
<body>
	<nav class="navbar navbar-primary" style="margin-bottom:0px;"
		role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" style="color: #ffffff" href="#">青岛埃米信息技术有限公司</a>
		<a data-toggle="modal" data-target="#myModal"
			style="position: absolute; right: 50px; color: #ffffff; top: 15px;">登录</a>
	</div>
	<div style="background-color: #00b3ee;"
		class="collapse navbar-collapse" id="example-navbar-collapse"></div>
	</nav>
	<div id="myAlert" style="margin-bottom:0px;display: none;" class="alert alert-warning">
	   <a href="#" class="close" data-dismiss="alert">&times;</a>
	   <strong>消息！</strong>用户名密码错误。
	</div>
	
	<div id="wating" style="margin-bottom:0px;display: none;" class="progress progress-striped active">
		   <div class="progress-bar progress-bar-info" role="progressbar" 
		      aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"  id="jcline"
		      style="width: 0%;">
		      <span class="sr-only"></span>
		   </div>
		</div>
	<div id="myCarousel" class="carousel slide">
		<!-- 轮播（Carousel）指标 -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
		</ol>
		<!-- 轮播（Carousel）项目 -->
		<div class="carousel-inner">
			<div class="item active">
				<img style="width: 1366px; height: 605px;" src="images/1-27.jpg"
					alt="First slide">
			</div>
			<div class="item">
				<img style="width: 1366px; height: 605px;" src="images/1-28.jpg"
					alt="Second slide">
			</div>
		</div>
	</div>


	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">请登录</h4>
				</div>
				<div class="modal-body">
					<form class="bs-example bs-example-form" role="form">
						<div class="input-group">
							<span class="input-group-addon">@</span> <input type="text"
								class="form-control" id="userName" placeholder="请输入用户名">
						</div>
						<br>
						<div class="input-group">
							<span class="input-group-addon">...</span> <input
								type="password" id="pwd" class="form-control" placeholder="请输入密码">
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button data-dismiss="modal" type="button" class="btn btn-primary btn-lg btn-block">登录</button>
				</div>
			</div>
		</div>
</body>
</html>