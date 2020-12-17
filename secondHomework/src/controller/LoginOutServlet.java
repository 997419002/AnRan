package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/loginOut.do")//匹配安全退出的servlet
public class LoginOutServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("进入安全退出的servlet");
	HttpSession session=request.getSession();
	//移除session中的isLogin属性
	session.removeAttribute("isLogin");
	session.removeAttribute("name");
	session.removeAttribute("role");
	session.removeAttribute("user");
	Cookie[] cookies=request.getCookies();
	for(Cookie c:cookies) {//把cookie的值都设为空
		c.setValue("");
	}
	response.sendRedirect("login.jsp");
}
}
