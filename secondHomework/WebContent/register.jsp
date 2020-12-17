<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>注册界面</title>
	<link rel="stylesheet" type="text/css" href="css/login.css"/>
	<script src="js/register.js"></script>
	<script src="js/jquery-3.4.1.min.js"></script>
</head>
<body>
<div class="admin_login_wrap">
    <h1 align="center">注册界面</h1>
    <div class="adming_login_border">
        <div class="admin_input">
                <ul class="admin_items">
                <li><a href="${pageContext.request.contextPath}/login.jsp">已有账号，请登录</a></li>
                    <li>
                        <label for="userName">用户名:</label>
                        <input type="text" name="userName"  id="1" size="40" class="admin_input_style" placeholder="用户名" onfocus="focusItem(this)" onblur="checkIsNull(this)"/>
                        <span id="userName"></span>
                    </li>
                    <li>
                        <label for="chrName">真实姓名:</label>
                        <input type="text" name="chrName"  id="2" size="40" class="admin_input_style" placeholder="真实姓名" onfocus="focusItem(this)" onblur="checkIsNull(this)"/>
                        <span id="chrName"></span>
                    </li>
                    <li>
                        <label for="email">电子邮箱:</label>
                        <input type="email" name="email"  id="3" size="40" class="admin_input_style" placeholder="电子邮箱" onfocus="focusItem(this)" onblur="checkIsNull(this)"/>
                        <span id="email"></span>
                    </li>
                    <li>
                    <label for="province">省份:</label>
                        <p><select id="province" name="provinceCode" onfocus="fillProvince()">
                        <option value="">请选择省份</option>
                        </select></p>
                        <span id="provinceError"></span>
                    </li>
                    <li>
                    <label for="city">城市:</label>
                        <p><select id="city" name="cityCode">
                        <option value="">请选择城市</option>
                        </select></p>
                        <span id="cityError"></span>
                    </li>
                    <li>
                        <label for="pwd">密码:</label>
                        <input type="password" name="userPwd" id="6" size="40"  class="admin_input_style" placeholder="密码" onfocus="focusItem(this)" onblur="checkIsNull(this)"/>
                          <span id="userPwd"></span>
                    </li>
                    <li>
                        <label for="repwd">确认密码:</label>
                        <input type="password" name="reuserPwd" id="7" size="40"  class="admin_input_style" placeholder="确认密码" onfocus="focusItem(this)" onblur="checkIsNull(this)"/>
                          <span id="reuserPwd"></span>
                    </li>
                    <li><input type="button" value="注册" onclick="ajaxCheckRegister()" style="text-align: center"/></li>
                </ul> 
        </div>
    </div>
</div>
</body>
</html>