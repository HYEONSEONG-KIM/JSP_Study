package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlindFilter implements Filter{

	private static final Logger logger = LoggerFactory.getLogger(BlindFilter.class);
	private Map<String, String> blindMap;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 초기화", getClass().getSimpleName());
		blindMap = new HashMap<String, String>();
		blindMap.put("127.0.0.1", "My IpAddress Filter");
		blindMap.put("0:0:0:0:0:0:0:1", "My IpAddress Filter");
		blindMap.put("192.168.44.37", "치우 필터링 ㅋㅋ");
		blindMap.put("192.168.44.35", "찬미 필터링 ㅋㅋ");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 누구라는 정보는 IP이용
		String messageView = "/13/blindMessage.jsp";
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
		// 현재 서버사이드 이므로 local은 서버, remote는 클라이언트
		String clientIP = req.getRemoteAddr();
		boolean pass =uri.equals(messageView) || !blindMap.containsKey(clientIP);
		
		if(!pass) {
			String reason = blindMap.get(clientIP);
			req.getSession().setAttribute("reason", reason);
			resp.sendRedirect(req.getContextPath() + messageView);;
		}else {
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {
		logger.info("{} 소멸", getClass().getSimpleName());
	}

}











