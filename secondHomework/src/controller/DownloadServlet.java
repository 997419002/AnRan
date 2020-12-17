package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/download")
public class DownloadServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String path=request.getParameter("path");
		String image=request.getParameter("image");
		String downloadPath=request.getServletContext().getRealPath("/"+path);
		response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(image, "utf-8"));
		InputStream in=new FileInputStream(downloadPath);
		int len=0;
		byte[] buffer=new byte[1024];
		ServletOutputStream out=response.getOutputStream();
		while((len=in.read(buffer))!=-1) {
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
		System.out.println("下载完整!");
	}
}
