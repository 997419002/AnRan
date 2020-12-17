<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>错误界面</title>
</head>
<body>
<div class="box">
<h2>抱歉，当前用户没有访问该资源的权限</h2>
    <h2><span class="clock">10</span>秒后自动返回到主界面，不能跳转请<a href="/FirstHomework/main1.jsp"><font color="red">点击这儿</font></a></h2>
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