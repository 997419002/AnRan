package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.UserDao;
import vo.User;
@WebServlet("/ajaxLoginCheck.do")
public class AjaxLoginServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	//获取所有所有的表单属性，用户名，密码，验证码和是否自动登录
	request.setCharacterEncoding("utf-8");
	String userName=request.getParameter("userName");
	String userPwd=request.getParameter("userPwd");
	String yourCode=request.getParameter("checkCode");
	String atuoLogin=request.getParameter("isSave");
	//获取session对象
	HttpSession session=request.getSession();
	//获取生成的验证码
	String sessionCheckCode=session.getAttribute("code").toString();
	//创建存放返回信息的map,注意创建的时候，因为map是接口，得创建它的实现类。
	Map<String,Object> map=new HashMap<String, Object>();
	if(!sessionCheckCode.equalsIgnoreCase(yourCode)) {
		//如果输入的验证码不正确，就将错误信息存放在map中
		map.put("code", 1);
		map.put("info","验证码输入不正确");
	}
	else {//如果验证码正确
		User user=UserDao.findUserByUserName(userName);//去数据库查询是否的有该用户名的用户
		if(user==null) {//如果用户名为空时， 错误信息为用户名不存在
			map.put("code", 2);
			map.put("info","用户名不存在");
		}
		else {//如果用户名存在
			if(!user.getUserPwd().equals(userPwd)) {
				//如果密码错误，将错误信息存放到map中
				map.put("code", 3);
				map.put("info", "密码错误");
			}
			else {
				//登录成功，则code=1
				map.put("code", 0);
				session.setAttribute("user", user);
				session.setAttribute("isLogin", "true");
				if(user.getRole().equals("管理员")) {
					session.setAttribute("role", "管理员");
					session.setAttribute("name", "");
				}
				else {
					session.setAttribute("role", "");
					session.setAttribute("name", user.getChrName());
				}
				if(atuoLogin!=null) {
				Cookie c1=new Cookie("userName", userName);
				c1.setMaxAge(60*60*24*7);
				response.addCookie(c1);
				Cookie c2=new Cookie("userPwd", userPwd);
				c2.setMaxAge(60*60*24*7);
				response.addCookie(c2);
				}
			}
		}
	}
	//将map集合转变为json格式的数据
		String jsonStr=new Gson().toJson(map);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.printf(jsonStr);
		pw.flush();
		pw.close();
}
}
