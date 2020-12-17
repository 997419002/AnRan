<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户管理</title>
<link rel="stylesheet" type="text/css" href="css/login.css"/>
<link rel="stylesheet" type="text/css" href="css/MyDiv.css"/>
<script src="js/userDao.js"></script>
	<script src="js/jquery-3.4.1.min.js"></script>
</head>
<body>
<div id="pageBody">
<div id="search">
<form id="searchForm">
<input type="text" placeholder="请输入用户名" id="userName">
<input type="text" placeholder="请输入真实姓名" id="chrName">
<input type="text" placeholder="请输入邮箱地址" id="email">
<input type="text" placeholder="请输入省份" id="province">
</form>
<div id="bt">
<a href="#" id="btSearch" onclick="queryUserAjax()">查找</a>
<a href="#" id="btClear">清空</a>
<a href="#" id="btInsert">增加</a>
<a href="#" id="btDelete">删除</a>
<a href="#" id="btUpdate">修改</a>
</div>
</div>
<table>
<thead>
<tr>
<th width=""><input type="checkbox" id="checkAll" title="选择"/></th>
<th class="bg" id="sortByUserName" data="userName">用户名</th>
<th width="110">中文名</th>
<th>邮箱</th>
<th width="70" class="bg" id="sortByProvinceName" data="province">省份</th>
<th width="70">城市</th>
<th width="120">操作</th>
</tr>
</thead>
</table>
<div class="paging"></div>
<div class="pageSize">每页
<select id="pageSize">
<option>5</option>
<option selected="selected">10</option>
<option>20</option>
</select>,共
<span id="total"></span>条数据
<span id="pageNumber">1</span>页/<span id="pageCount"></span>页
</div>
<div class="pageNav">
<a href="" id="first"></a>
<a href="" id="back"></a>
<a href="" id="next"></a>
<a href="" id="last"></a>
</div>
</div>
<div id="fade" class="black_overlay" onclick="closeDiv('MyDiv','fade')"></div>
<div id="MyDiv" class="white_content"><div style="text-align:right;height:20px;">
<span style="font-size:24px;cursor:pointer;" title="点击关闭" onclick="CloseDic('MyDiv','fade')"></span>
</div>
<div>
				<input id="action" name="action" type="text" hidden />
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
                    <li><input type="button" value="确定" id="sure"/></li>
</div>
</div>
</body>
</html>