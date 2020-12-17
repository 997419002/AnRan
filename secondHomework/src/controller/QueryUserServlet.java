package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.UserDao;
import vo.Page;
import vo.User;
@WebServlet("/queryUser.do")
public class QueryUserServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String queryParams=request.getParameter("queryParams");
	String pageParams=request.getParameter("pageParams");
	Gson gson=new GsonBuilder().serializeNulls().create();
	HashMap<String,Object> mapPage=gson.fromJson(pageParams,HashMap.class);
	Page page=Page.getPageParams(mapPage);
	User user=new User();
	if(queryParams!=null) {
		user=gson.fromJson(queryParams, User.class);
	}
	ArrayList<User> rows=UserDao.query(user, page);
	int total=UserDao.count(user, page);
	HashMap<String,Object> mapReturn=new HashMap<String, Object>();
	mapReturn.put("rows", rows);
	mapReturn.put("total", total);
	String jsonStr=gson.toJson(mapReturn);
	response.setContentType("text/html;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.printf(jsonStr);
	pw.flush();
	pw.close();
	
}
}
