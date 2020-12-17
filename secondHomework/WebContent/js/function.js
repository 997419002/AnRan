function changeCode(img){//使用js，传过来img标签对象，点一下就触发一次这个函数
	img.src="/FirstHomework/imageCode.do?"+new Date().getTime();//点击更换一下参数。
}
function jqAjaxCheckLogin(){
	//每次触发点击事件都将错误信息清空
	$("#checkCode").text("");
	$("#userName").text("");
	$("#userPwd").text("");
	//将登陆的按钮改为button，添加onclick事件，如果点击，这通过id选择器获取对应的值，进行局部判断
	$.ajax({
		type:"post",
		url:"ajaxLoginCheck.do",
		data:{userName:$("#user").val(),userPwd:$("#pwd").val(),checkCode:$("#code").val(),isSave:$("#save").val()},
		dataType:"json",
		success:function(response){
			if(response.code==0){//如果登陆成功，则跳转到主界面
				window.location.href="main1.jsp";
			}
			else if(response.code==1){
				$("#checkCode").text(response.info);
				$("#checkCode").css({color:"red"});
			}
			else if(response.code==2){
				$("#userName").text(response.info);
				$("#userName").css({color:"red"});
			}
			else if(response.code==3){
				$("#userPwd").text(response.info);
				$("#userPwd").css({color:"red"});
			}
		},
	});	
}
function focusItem(obj){
	$(obj).next('label').text("");
}
function checkIsNull(obj){
	if($(obj).val()==""){
	switch($(obj).attr("name")){
	case "userName":
		$(obj).next('label').text("用户名不能为空");
		$(obj).next('label').css({color:"red"});
		break;
	case "userPwd":
		$(obj).next('label').text("密码不能为空");
		$(obj).next('label').css({color:"red"});
		break;
	case "checkCode":
		$(obj).next('label').text("验证码不能为空");
		$(obj).next('label').css({color:"red"});
		break;
	}
	}
}