package kr.or.ddit.servlet01;

import javax.servlet.http.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

// 단 , Context name : third
// service name : /imageView
public class ImageServlet extends HttpServlet{
	
	ServletContext application = null;
	File contentsFolder = null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		application = getServletContext();
		String contentsPath = application.getInitParameter("contentsPath");
		contentsFolder = new File(contentsPath);
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws IOException, ServletException
	
	{
		String mime = "image/jpeg";
		resp.setContentType(mime);
		
		String imageName = req.getParameter("+-b");
		if(imageName == null || imageName.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
//		String source = "C:/contents/" + imageName;
		// contentsFolder 가 널일경우의 처리...??
		File srcFile = new File(contentsFolder,imageName);
		FileInputStream fis = new FileInputStream(srcFile);
		OutputStream os = resp.getOutputStream();
		byte[] buffer = new byte[1024];
		int pointer = -1;
		while((pointer = fis.read(buffer))!= -1){
			os.write(buffer, 0, pointer);
		}
		fis.close();
		os.close();
	}
	
}