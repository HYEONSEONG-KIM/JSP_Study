package kr.or.ddit.servlet01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageListServlet extends HttpServlet{
	String contentsPath = null;
	ServletContext application = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		contentsPath = application.getInitParameter("contentsPath");
//		System.out.printf("%s 서블릿 초기화\n", getClass().getName());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		File contentsFolder = new File(contentsPath);
		String[] imageList = contentsFolder.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// main/sub; charset=encoding
				String mimeText = application.getMimeType(name);
				return mimeText != null && mimeText.startsWith("image/");
			}
		});

		String pattern = "<option>%s</option>";
		StringBuffer options = new StringBuffer();
		for(String name: imageList) {
			options.append(String.format(pattern, name));
			
		}
		// 클래스 로더 사용하므로 imageList.tmpl의 경로를 찾음
		InputStream is = getClass().getResourceAsStream("imageList.tmpl");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String tmp = null;
		StringBuffer tmplSource = new StringBuffer();
		while((tmp = reader.readLine()) != null) {
			tmplSource.append(tmp + "\n");
		}
		
		String html = tmplSource.toString().replace("#{data}", options);
		
		resp.getWriter().println(html);
	}
}








