package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumtype.MimeType;

@WebServlet("/05/factorial")
public class factorialServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int left = 0;
		if(req.getParameter("left") != null && !req.getParameter("left").isEmpty()) {
			left = Integer.parseInt(req.getParameter("left"));
		}
		
		int result = 1;
		for(int i = 1; i <= left; i++) {
			result *= i;
		}
		
		StringBuffer restulStr = new StringBuffer(left + "!=" + result); 
		
		String accept = req.getHeader("Accept");
		MimeType mime = MimeType.findMimeType(accept);
		
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("left", left);
		dataMap.put("operation", "!");
		dataMap.put("expression", restulStr);
		
		Object data = null;
		
		
		if(MimeType.JSON.equals(mime)) {
			ObjectMapper mapper = new ObjectMapper();
			data = mapper.writeValueAsString(dataMap);
		}else if(MimeType.PLAIN.equals(mime)) {
			data = restulStr;
		}else if(MimeType.XML.equals(mime)) {
			restulStr.setLength(0);
			restulStr.append("<result>");
			restulStr.append("<left>" + left+"</left>");
			restulStr.append("<expression>" +left + "! =" + result +"</expression>");
			restulStr.append("</result>");
			data = restulStr;
			
		}
		
		resp.setContentType(mime.getMimeText());
		
		
		try(
				PrintWriter out = resp.getWriter();
		){
			out.println(data);
		}
		
		
		
	}
	
}
