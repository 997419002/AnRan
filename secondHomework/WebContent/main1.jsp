<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>比较新颖的主界面实例</title>
<meta charset="utf-8">
<meta name="baidu-site-verification" content="b9lOcChmbf">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
<link rel="shortcut icon" href="https://www.mosq.cn/favicon.ico" />
<link rel="stylesheet" href="css/main.css">
<noscript>
<link rel="stylesheet" href="css/noscript.css"/>
</noscript>
</head>
<body>
<% 
HttpSession s=request.getSession();
String flag=(String)s.getAttribute("isLogin");
PrintWriter pw=response.getWriter();
if(!"true".equals(flag)){
	pw.write("<script>");
	pw.write("location.href='error.jsp'");
	pw.write("</script>");
}
else{
	out.write("");
}
%>
<div id="wrapper">
	<header id="header">
	<div class="logo">
		<img src="https://www.mosq.cn/usr/uploads/sina/5cc3f8bf588ff.jpg
" class="logo">
	</div>
	<div class="content">
		<div class="inner">
			<h1>主页</h1>
			<p>欢迎你:${sessionScope.role}${sessionScope.name}<a href="${pageContext.request.contextPath}/loginOut.do">
			<font color="red">[安全退出]</font></a></p>
		</div>
	</div>
	<nav>
		<script type="text/javascript">
			function duihua()
			{
			alert("森七")
			}
		</script>
	<ul>
	<li><a href="${pageContext.request.contextPath}/main1.jsp" >首页</a></li>
		<li><a href="${pageContext.request.contextPath}/downloadList.do" >资源下载</a></li><!-- 按钮1-->
		<li><a href="${pageContext.request.contextPath}/admin_user.jsp" >用户管理</a></li><!-- 按钮2-->
		<li><a href="${pageContext.request.contextPath}/admin_download.jsp" >资源管理</a></li><!-- 按钮3-->
		<li><a href="${pageContext.request.contextPath}/personal.jsp" >个人中心</a></li><!-- 按钮5-->
	</ul>
	</nav></header>
	<footer id="footer">
	<p class="copyright">©2020-09-24</p><!-- 版权-->
	</footer>
</div>
<div id="bg">
</div>
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/util.js"></script>
<script src="js/main.js"></script>
<script type="text/javascript">document.oncontextmenu=function(t){window.event&&(t=window.event);try{var e=t.srcElement;return"INPUT"==e.tagName&&"text"==e.type.toLowerCase()||"TEXTAREA"==e.tagName?!0:!1}catch(n){return!1}},document.onpaste=function(t){window.event&&(t=window.event);try{var e=t.srcElement;return"INPUT"==e.tagName&&"text"==e.type.toLowerCase()||"TEXTAREA"==e.tagName?!0:!1}catch(n){return!1}},document.oncopy=function(t){window.event&&(t=window.event);try{var e=t.srcElement;return"INPUT"==e.tagName&&"text"==e.type.toLowerCase()||"TEXTAREA"==e.tagName?!0:!1}catch(n){return!1}},document.oncut=function(t){window.event&&(t=window.event);try{var e=t.srcElement;return"INPUT"==e.tagName&&"text"==e.type.toLowerCase()||"TEXTAREA"==e.tagName?!0:!1}catch(n){return!1}},document.onselectstart=function(t){window.event&&(t=window.event);try{var e=t.srcElement;return"INPUT"==e.tagName&&"text"==e.type.toLowerCase()||"TEXTAREA"==e.tagName?!0:!1}catch(n){return!1}};</script>
</body>
</html>