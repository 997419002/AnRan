<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>登录界面</title>
	<link rel="stylesheet" type="text/css" href="css/login.css"/>
	<script src="js/function.js"></script>
	<script src="js/jquery-3.4.1.min.js"></script>
</head>
<body>
<div class="admin_login_wrap">
    <h1 align="center">登录界面</h1>
    <div class="adming_login_border">
        <div class="admin_input">
                <ul class="admin_items">
                    <li>
                        <label for="user">用户名：</label>
                        <input type="text" name="userName"  id="user" size="40" class="admin_input_style" onblur="checkIsNull(this)" onfocus="focusItem(this)"/>
                       <label id="userName"></label>
                    </li>
                    <li>
                        <label for="pwd">密码：</label>
                        <input type="password" name="userPwd"  id="pwd" size="40" class="admin_input_style" onblur="checkIsNull(this)" onfocus="focusItem(this)"/>
                        <label id="userPwd"></label>
                    </li>
                    <li>
                        <label for="pwd">验证码：</label>
                        <input type="text" name="checkCode"  id="code" size="40"  class="admin_input_style" onblur="checkIsNull(this)" onfocus="focusItem(this)"/>
                        <label id="checkCode"></label>
                    </li>
                    <input type="checkbox" name="isSave" value="true" size="40" id="save" class="admin_input_style"/>一周以内免费登录
                    <li>
                    </li>
                    <li>
                    <label><img src="${pageContext.request.contextPath}/imageCode.do" onclick="changeCode(this)" class="admin_input_style" style="text-align: center"></label>
                    </li>
                    <li>
                        <input type="button" tabindex="3" value="提交" class="btn btn-primary" onclick="jqAjaxCheckLogin()"/>
                    </li>
                    <li>
                    <a href="${pageContext.request.contextPath}/register.jsp" class="admin_input_style"><font>点击注册账号!</font></a>
                    </li>
                </ul>
        </div>
    </div>
</div>
</body>
</html>