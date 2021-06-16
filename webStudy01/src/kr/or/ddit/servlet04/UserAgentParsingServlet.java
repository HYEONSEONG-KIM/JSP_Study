package kr.or.ddit.servlet04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumtype.BrowserType;
import kr.or.ddit.enumtype.MimeType;
import kr.or.ddit.enumtype.OSType;

@WebServlet("/04/getBrowserName")
public class UserAgentParsingServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String accept = req.getHeader("Accept");
		String userAgent = req.getHeader("user-agent");
		
		
		String browser = BrowserType.paeseUserAgent(userAgent);
		String os = OSType.findUserOs(userAgent);
		Map<String, Object> target = new HashMap<>();
		target.put("browser", browser);
		target.put("os", os);
		StringBuffer json = new StringBuffer();
		String PROPPTRN = "\"%s\" : \"%s\" ,";
		// Marshalling : native로 표현된 데이터를 공통 표현방식(xml, json)으로 바꾸는 과정
		// Unmarshalling : 공통 표현 방식으로 표현된 데이터를 native 방식으로 바꾸는 과정
		json.append("{");
		for(Entry<String,Object> entry :target.entrySet()) {
			json.append(String.format(PROPPTRN, entry.getKey(), Objects.toString(entry.getValue(), "")));
		}
		json.append("}");
		
		int lastIdx = json.lastIndexOf(",");
		if(lastIdx >= 0) {
			json.deleteCharAt(lastIdx);
		}
		
		MimeType mime = MimeType.findMimeType(accept);
		Object data = null;
		if(MimeType.JSON.equals(mime)) {
			data = json;
		}else {
			data = os + "," + browser;
		}
		resp.setContentType(mime.getMimeText());
		try(
			PrintWriter out =  resp.getWriter();
		){
			out.println(data);
		}
	}
}
