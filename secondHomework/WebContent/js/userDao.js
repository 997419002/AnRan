function queryUserAjax(){
	$.ajax({
		type:"post",
		url:"queryUser.do",
		data:{queryParams:{userName:$("#userName").val(),chrName:$("#chrName").val(),emai:$("email").val(),province:$("province").val()},
			pageParams:{pageSize:$("#pageSize").val(),pageNumber:$("#pageNumber").val(),sort:$(".bg").val(),sortOrder:$("#sort").val()}},
		dataType:"json",
		success:function(response){
			var rows=response.rows;
			total=response.total;
			pageCount=Math.ceil(total/pageSize);
			$("#total").text(pageCount);
			$("#pageCount").text(pageCount);
			$("tbody").empty();
			$.each(rows,function(index,row){
				var s=JSON.stringify(row);
				var str="<tr data='"+s+"'>";
				str=str+'<td><input type="checked" value='+row.userName+'/></td>';
				str=str+'<td>'+row.chrName+'</td>';
				str=str+'<td>'+row.email+'</td>';
				str=str+'<td>'+row.province+'</td>';
				str=str+'<td>'+row.city+'</td>';
				str=str+'<td><a href="#" id="btnDel" value='+row.userName+'>删除<a/>';
				str=str+'<a href="#" id="btnUpdate">修改</a></td>';
				str=str+'</tr>';
				$("tbody").append(str);
			});
			$('tbody tr:even').addClass('tr_even');
			$('tbody tr:add').addClass('rd_odd');
		}
	});
}
$("#btDelete").click(function(){
	var len=$('tbody tr input:checkbox:checked').length;
	if(len==0){
		alert("至少需要选择一项!");
		return;
	}
	var vals=[];
	$('tbody tr input:checked:checked').each(function(index,item){
		vals.push($(this).val());
	});
	$.ajax({
		type:"post",
		url:"deleteUser.do",
		data:{ids:vals.join(",")},
		dataType:"json",
		success:function(response){
			alert(response.info);
			if(response.code==0){
				reload();
			}
		}
	});
});
$('table').on('click','#btnDel',function(){
	var userName=$(this).attr("value");
	$.ajax({
		type:"post",
		url:"deleteUser.do",
		data:{ids:userName},
		dataType:"json",
		success:function(response){
			alert(response.info);
			if(response.code==0){
				reload();
			}
		}
	});
});
$("#sure").click(function(){
	 update();
	 
});
$('table').on('click','#btnUpdate',function(){
	showDiv(show_div,bg_div);
});
function update(){
	//每次触发点击事件都将错误信息清空
	$("#userName").text("");
	$("#chrName").text("");
	$("#email").text("");
	$("#userPwd").text("");
	$("#reuserPwd").text("");
	//将登陆的按钮改为button，添加onclick事件，如果点击，这通过id选择器获取对应的值，进行局部判断
	$.ajax({
		type:"post",
		url:"userUpdate.do",
		data:{userName:$("#1").val(),chrName:$("#2").val(),email:$("#3").val(),province:$("#province").val(),city:$("#city").val(),
			userPwd:$("#6").val(),reuserPwd:$("#7").val()},
		dataType:"json",
		success:function(response){
			if(response.code==0){
				window.location.href="admin_user.jsp";
				 closeDiv(show_div,bg_div);
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
function showDiv(show_div,bg_div){
	document.getElementById(show_div).style.display="block";
	document.getElementById(bg_div).style.display="block";
	var windowHeight=$(window).height();
	var windowWidth=$(window).width();
	var popupHeight=$("#"+show_div).height();
	var popupWeight=$("#"+show_div).width();
	var posiTop=(windowHeight-popupHeight)/2;
	var posiLeft=(windowWidth-popupWeight)/2;
	$("#"+show_div).css({"left":posiLeft+"px","top":posiTop+"px","display":"block"});	
}
function closeDiv(show_div,bg_div){
	document.getElementById(show_div).style.display="none";
	document.getElementById(bg_div).style.display="none";
}
//页面加载，两个参数均为空，显示第一面数据
$(document).ready(function(){
	queryUserAjax();
});