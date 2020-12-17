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

import dao.UserDao;
@WebServlet("/userDelete.do")
public class UserDeleteServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	request.setCharacterEncoding("utf-8");
	String ids=request.getParameter("ids");
	Map<String,Object> map=new HashMap<String, Object>();
	if(UserDao.delete(ids)) {
		map.put("code", 0);
	}
	String jsonStr=new Gson().toJson(map);
	response.setContentType("text/html;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.printf(jsonStr);
	pw.flush();
	pw.close();
}
}
