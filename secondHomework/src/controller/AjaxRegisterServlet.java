package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.CityDao;
import dao.ProvinceDao;
import dao.UserDao;
import vo.User;
@WebServlet("/ajaxRegisterCheck.do")
public class AjaxRegisterServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	request.setCharacterEncoding("utf=8");
	String userName=request.getParameter("userName");
	String chrName=request.getParameter("chrName");
	String email=request.getParameter("email");
	String province=request.getParameter("province");
	String city=request.getParameter("city");
	String userPwd=request.getParameter("userPwd");
	String reuserPwd=request.getParameter("reuserPwd");
	Map<String,Object> map=new HashMap<String, Object>();
	User u=UserDao.findUserByUserName(userName);
	boolean nameFlag=false;
	boolean chrFlag=false;
	boolean emailFlag=false;
	boolean pwdFlag=false;
	boolean repwdFlag=false;
	if(u!=null) {//该用户存在时
		map.put("code", 1);
		map.put("info", "该用户已被注册");
	}
	else {
		if(!userName.matches("^[a-zA-Z]/w{4,15}")) {
			map.put("code", 2);
			map.put("info", "用户名的格式不合法");	
		}
		else {
			map.put("code", 3);
			nameFlag=true;
		}
	}
	if(!chrName.matches("[\\u4e00-\\u9fa5]{2,4}")) {
	map.put("code", 4);
	map.put("info", "真实姓名不合法");
	}
	else {
		map.put("code", 5);
		chrFlag=true;
	}
	if(!email.matches("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}")) {
		map.put("code", 6);
		map.put("info", "邮箱不合法");
	}
	else {
		map.put("code", 7);
		emailFlag=true;
	}
	if(userPwd.length()<4) {
		map.put("code", 8);
		map.put("info", "密码长度不合法");
	}
	else {
		map.put("code", 9);
		pwdFlag=true;
	}
	if(!userPwd.equals(reuserPwd)) {
		map.put("code", 10);
		map.put("info", "两次输入的密码不一致");
	}
	else {
		map.put("code", 11);
		repwdFlag=true;
	}
	if(nameFlag&&chrFlag&&emailFlag&&pwdFlag&&repwdFlag) {
		map.put("code", 0);
		User user=new User(userName, userPwd, chrName, "用户", email, ProvinceDao.getProvinceCode(province),province, CityDao.getCityCode(city),city);
		UserDao.insertUser(user);
	}
	String jsonStr=new Gson().toJson(map);
	response.setContentType("text/html;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.printf(jsonStr);
	pw.flush();
	pw.close();
}
}
