<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.io.PrintWriter"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>下载界面</title>
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
<table border="1" style="text-align: center; width: 1500px; height: 500px;">
<tr>下载列表</tr>
<tr>
<td>编号</td>
<td>文件名</td>
<td>文件路径</td>
<td>文件描述</td>
<td>文件大小</td>
<td>星级</td>
<td>下载</td>
</tr>
<c:forEach items="${requestScope.list}" var="l">
<tr>
<td>${l.id}</td>
<td>${l.name}</td>
<td><img src="${l.path}" width="50" height="50"/></td>
<td>${l.description}</td>
<td>${l.size}</td>
<td>${l.star}</td>
<td><a href="${pageContext.request.contextPath}/download?path=${l.path}&image=${l.image}"><font color="red">立即下载</font></a></td>
</tr>
</c:forEach>
</table>
</body>
</html>