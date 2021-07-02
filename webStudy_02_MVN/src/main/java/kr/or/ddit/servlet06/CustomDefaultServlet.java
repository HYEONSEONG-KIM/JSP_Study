package kr.or.ddit.servlet06;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// url패턴에 해당 폴더 경로로 캐시 데이터 제어 가능
//@WebServlet("/")
public class CustomDefaultServlet extends HttpServlet{
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String cPath = req.getContextPath();
		uri = uri.substring(cPath.length());
		//InputStream is = application.getResourceAsStream(uri);
		URL resUrl = application.getResource(uri);
		if(resUrl ==null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, uri);
			return;
		}
		
		resp.setHeader("Cache-Control", "no-cache");
		resp.addHeader("Cache-Control", "no-store");
		resp.setHeader("Pragma", "no-cache");
		resp.addHeader("Pragma", "no-store");
		resp.setDateHeader("Expires", 0);
		
		try {
			Path resPath = Paths.get(resUrl.toURI());
			OutputStream os = resp.getOutputStream();
			Files.copy(resPath, os);
		
			os.close();
			
		} catch (URISyntaxException e) {
			// 예외를 내가 강제로 발생
			throw new IOException(e);
		}
		
		
	}
}















