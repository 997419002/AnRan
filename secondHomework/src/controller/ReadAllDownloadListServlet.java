package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DownloadDao;
import vo.Download;
@WebServlet("/downloadList.do")
public class ReadAllDownloadListServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		///获取所有的下载文件，放到集合中
		List<Download> list=DownloadDao.getAllDowload();
		//将集合放在request的请求中
		request.setAttribute("list", list);
		//然后转发该请求
		request.getRequestDispatcher("downloadList.jsp").forward(request, response);
	}
}
