package kr.or.ddit.servlet02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.security.pkcs11.Secmod.DbMode;

public abstract class ReadTmplServlet extends HttpServlet{
	// 서버와의 커뮤케이션을 하기위해 사용
	// 서버 - 서블릿  - 어플리케이션의 구조
	ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuffer template = readTemplate(req);
		makeData(req);
		String mime = getMime();
		resp.setContentType(mime);
		makeResponseContents(template, req, resp);
	}
	
	protected abstract String getMime();
	
	// 1. tmpl읽기
	private StringBuffer readTemplate(HttpServletRequest req) throws IOException {// service-> 톰캣으로 예외처리
		StringBuffer template = null;
		// tmpl의 경로가 webContent안에 있으므로 그 경로는 servletPath를 이용하여 구함
		String tmplPath = req.getServletPath();
		// 구한 경로에서 해당 tmpl의 스트림을 받아와 문서 읽기
		InputStream is = application.getResourceAsStream(tmplPath);
		if(is != null) {
			try(
				// 1.7이후 부터 적용 -> close할 스트림을 try()안에 선언하면 별로의 close작업 생략
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			){
				template = new StringBuffer();
				String tmp = null;
				
				while((tmp = reader.readLine()) != null) {
					template.append(String.format("%s\n", tmp));
				}
			}
			
		}
		return template;
	}
	// 2. 데이터 만들기(**)
	protected abstract void makeData(HttpServletRequest req);
	
	// 3. 실제 데이터로 구멍을 치환
	// 4. 컨텐츠로 응답 전송
	private void makeResponseContents(StringBuffer template, 
								HttpServletRequest req, 
								HttpServletResponse resp) throws IOException
	{
		if(template==null) return;
		// resp는 한번만 응답할 수 있는 객체인데 이미 실행되었는지 여부 판단
		if(resp.isCommitted()) return;
		
		
		String tmplSrc = template.toString();
		// tmpl에서 설정한 식별자를 구분하기 위한 정규식
		Pattern regex = Pattern.compile("#\\{([\\w_]+)\\}");
		// Matcher??
		Matcher matcher = regex.matcher(tmplSrc);
		StringBuffer html = new StringBuffer();
		while(matcher.find()) {
			// 해당 정규식에 맞는 name값 담기
			String name = matcher.group(1);
			Object data = req.getAttribute(name);
			// 식별에 해당하는 name값과 같은 곳에 입력한 데이터를 지환
			matcher.appendReplacement(html, Objects.toString(data, ""));
		}
		matcher.appendTail(html);
		
		try(
				PrintWriter out = resp.getWriter();
		){
			out.print(html);
		}
	}
	
}
