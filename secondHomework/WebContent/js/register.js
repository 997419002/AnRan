function checkIsNull(obj){
	if($(obj).val()==""){
		switch($(obj).attr("name")){
		case "userName":
			$(obj).next('span').text("用户名不能为空");
			$(obj).next('span').css({color:"red"});
			break;
		case "chrName":
			$(obj).next('span').text("真实姓名不能为空");
			$(obj).next('span').css({color:"red"});
			break;
		case "email":
			$(obj).next('span').text("邮箱不能为空");
			$(obj).next('span').css({color:"red"});
			break;
		case "userPwd":
			$(obj).next('span').text("密码不能为空");
			$(obj).next('span').css({color:"red"});
			break;
		case "reuserPwd":
			$(obj).next('span').text("确认密码不能为空");
			$(obj).next('span').css({color:"red"});
			break;
		}
		}
}
function ajaxCheckRegister(){
	//每次触发点击事件都将错误信息清空
	$("#userName").text("");
	$("#chrName").text("");
	$("#email").text("");
	$("#userPwd").text("");
	$("#reuserPwd").text("");
	//将登陆的按钮改为button，添加onclick事件，如果点击，这通过id选择器获取对应的值，进行局部判断
	$.ajax({
		type:"post",
		url:"ajaxRegisterCheck.do",
		data:{userName:$("#1").val(),chrName:$("#2").val(),email:$("#3").val(),province:$("#province").val(),city:$("#city").val(),
			userPwd:$("#6").val(),reuserPwd:$("#7").val()},
		dataType:"json",
		success:function(response){
			if(response.code==0){//如果登陆成功，则跳转到主界面
				window.location.href="login.jsp";
			}
			else if(response.code==1){
				$("#userName").text(response.info);
				$("#userName").css({color:"red"});
			}
			else if(response.code==2){
				$("#userName").text(response.info);
				$("#userName").css({color:"red"});
			}
			else if(response.code==3){
				$("#userName").text("√");
				$("#userName").css({color:"green"});
			}
			else if(response.code==4){
				$("#chrName").text(response.info);
				$("#chrName").css({color:"red"});
			}
			else if(response.code==5){
				$("#chrName").text("√");
				$("#chrName").css({color:"green"});
			}
			else if(response.code==6){
				$("#email").text(response.info);
				$("#email").css({color:"red"});
			}
			else if(response.code==7){
				$("#email").text("√");
				$("#email").css({color:"green"});
			}
			else if(response.code==8){
				$("#userPwd").text(response.info);
				$("#userPwd").css({color:"red"});
			}
			else if(response.code==9){
				$("#userPwd").text("√");
				$("#userPwd").css({color:"green"});
			}
			else if(response.code==10){
				$("#reuserPwd").text(response.info);
				$("#reuserPwd").css({color:"red"});
			}
			else if(response.code==11){
				$("#userPwd").text("√");
				$("#userPwd").css({color:"green"});
			}
		},
	});	
}
function focusItem(obj){
	$(obj).next('span').text("");
}
function fillProvince(){
	$.ajax({
		type:"post",
		url:"queryProvinceCity.do",
		data:{},
		dataType:"json",
		success:function(response){
			var provinceElement=document.getElementById("province");
			//清除所有的select
			provinceElement.options.length=0;
			//循环添加其他选项
			provinceElement.add(new Option("请选择省份",""));
			for(index=0;index<response.length;index++){
				provinceElement.add(new Option(response[index].name,response[index].id));
			}
		}
	});
}
$(document).ready(function(){
	fillProvince();
	$("#province").change(function(){
		$("#city").empty;
		$("#city").append($("<option>").val("").text("请选择城市"));
		if($(this).val()==""){
			$("#provinceError").css("color","red");
			$("#provinceError").text("必须选择省份!");
			return;
		}
		province_correct=true;
		$("#provinceError").text("");
		var provinceCode=$("#province").val();
		$.ajax({
			type:"post",
			url:"queryProvinceCity.do",
			data:{provinceCode:provinceCode},
			dataType:"json",
			success:function(response){
				for(index=0;index<response.length;index++){
					var option=$("<option>").val(response[index].id).text(response[index].name);
					$("#city").append(option);
				}
			}
		});
});
});