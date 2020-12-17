package filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import vo.User;
@WebFilter("/*")
public class PermissionFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse reps=(HttpServletResponse)response;
		reps.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		String requestURI=req.getRequestURI();//获取请求包含项目的请求路径
		String contextPath=req.getContextPath();//获取的是/项目名
		String url=requestURI.substring(contextPath.length());//获取项目名后面的请求信息.
		String userName=null;
		if(url.contains("imageCode.do")||url.contains("login.do")||url.contains("error.jsp")||url.contains("loginOut.do")||
				url.contains("login.jsp")||url.contains("error1.jsp")) {
			//请求登录，验证码和错误时放行
			chain.doFilter(request, response);
		}
		else if(url.contains("main1.jsp")){//匹配主界面
			Cookie[] cookies=req.getCookies();
			if(cookies==null) {
			}
			else {
			for(Cookie c:cookies) {
				if("userName".equals(c.getName())){
					userName=c.getValue();
				}
			}
			if(userName!=null) {
				HttpSession session=req.getSession();
				User user=UserDao.findUserByUserName(userName);
				session.setAttribute("isLogin", "true");
				if(user.getRole().equals("管理员")) {
					session.setAttribute("role", "管理员");
					session.setAttribute("name", "");
				}
				else {
					session.setAttribute("role", "");
					session.setAttribute("name", user.getChrName());
				}
			}
			else {
				//什么也不干
			}
			}
			chain.doFilter(request, response);
		}
		else if(url.contains("admin_")) {
			System.out.println("进入adminif");
			HttpSession session=req.getSession();
			String role=(String)session.getAttribute("role");
			if(role=="") {
				PrintWriter out=reps.getWriter();
				out.write("<script>");
				out.write("location.href='error1.jsp'");
				out.write("</script>");
				out.close();
			}
			else if(role==null) {
				PrintWriter out=reps.getWriter();
				out.write("<script>");
				out.write("location.href='error.jsp'");
				out.write("</script>");
				out.close();
			}
			else {
				chain.doFilter(request, response);
			}
		}
		else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub		
	}

}
