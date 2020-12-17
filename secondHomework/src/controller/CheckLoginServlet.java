package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import vo.User;
@WebServlet("/login.do")//匹配后缀为login.do的servlet
public class CheckLoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("进入检查登录的servlet");
		HttpSession session=request.getSession();
		//设置字符集，防止乱码
		request.setCharacterEncoding("utf-8");
		//分别获取用户名密码吗和验证码的值
		String userName=request.getParameter("userName");
		String userPwd=request.getParameter("userPwd");
		String yourCheckCode=request.getParameter("checkCode");
		//获取是否一周保存用户名和密码
		String isSave=request.getParameter("isSave");
		System.out.println(isSave);
		//获取客户端验证码
		String sessionCheckCode=session.getAttribute("code").toString();
		//根据输入的用户名找用户
		User user=UserDao.findUserByUserName(userName);
		if(user==null) {
			request.setAttribute("msg", "您输入的用户名不存在!");
			request.getRequestDispatcher("error.jsp").forward(request, response);;
		}
		else if(!sessionCheckCode.equalsIgnoreCase(yourCheckCode)) {
			request.setAttribute("msg", "您输入的验证码不正确!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		else {
			if(!user.getUserPwd().equals(userPwd)) {
				request.setAttribute("msg", "您输入的密码不正确!");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			else {//登陆成功
				if("true".equals(isSave)) {
					System.out.println("进入此if");
					Cookie c1=new Cookie("userName", userName);
					c1.setMaxAge(60*60*24*7);
					response.addCookie(c1);
					Cookie c2=new Cookie("userPwd", userPwd);
					c2.setMaxAge(60*60*24*7);
					response.addCookie(c2);
				}
				else {
				}
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
				response.sendRedirect("main1.jsp");
			}
		}
	}
}
