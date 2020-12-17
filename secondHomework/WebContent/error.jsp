<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>错误界面</title>
</head>
<body>
<%
String msg=(String)request.getAttribute("msg");
if(msg==null){
	out.write("抱歉，您必须先登录才能访问该资源");
}
else{
out.write(msg);
}
%>
<div class="box">
    <h2><span class="clock">10</span>秒后自动返回到登录界面，不能跳转请<a href="/FirstHomework/login.jsp"><font color="red">点击这儿</font></a></h2>
</div>
<script>
	var t = 10;
	var time = document.getElementsByClassName("clock")[0];
	function fun() {
		t--;
		time.innerHTML = t;
		if(t <= 0) {
			 location.href = "/FirstHomework/login.jsp";
			clearInterval(inter);
		}
	}
	var inter = setInterval("fun()", 1000);
</script>
</body>
</html>