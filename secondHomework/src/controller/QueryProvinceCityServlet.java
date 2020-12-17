package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.CityDao;
import dao.ProvinceDao;
import vo.City;
import vo.Province;
@WebServlet("/queryProvinceCity.do")
public class QueryProvinceCityServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String provinceCode=request.getParameter("provinceCode");
	String jsonStr="";
	if(provinceCode==null) {//没有请求参数表示所有省份
		List<Province> provinces=ProvinceDao.getAllProvince();
		jsonStr=new Gson().toJson(provinces);
	}
	else {
		List<City> citys=CityDao.getCityByPid(Integer.parseInt(provinceCode));
		jsonStr=new Gson().toJson(citys);
	}
	response.setContentType("text/html;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.print(jsonStr);
	pw.flush();
	pw.close();
}
}
