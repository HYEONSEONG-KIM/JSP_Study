package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumtype.MimeType;

@WebServlet("/05/messageService")
public class MessageServiceServlet extends HttpServlet{
	
	ResourceBundle bundle;
	Map<String, Object> map;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String basename = config.getInitParameter("basename");
		bundle = ResourceBundle.getBundle("kr.or.ddit.servlet05.message");
		map = new HashMap<>();

		Enumeration<String> enumeration = bundle.getKeys();
		
		while(enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();
			map.put(key, bundle.getObject(key));
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 0. mime 설정
		String accept = req.getHeader("Accept");
		MimeType mime = MimeType.findMimeType(accept);
		
		if(!MimeType.JSON.equals(mime)) {
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return;
		}
		
		
		// marshalling & 직렬화 
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType(mime.getMimeText());
		
		try(
				PrintWriter out = resp.getWriter();
		){
			mapper.writeValue(out, map);
		}
		
	}
}
