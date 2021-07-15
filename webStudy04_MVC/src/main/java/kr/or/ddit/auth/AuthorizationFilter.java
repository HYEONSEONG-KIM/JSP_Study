package kr.or.ddit.auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.MemberVO;

public class AuthorizationFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
	private ServletContext application;
	private Map<String, String[]> securedMap;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 초기화", getClass().getSimpleName());
		application = filterConfig.getServletContext();
	}
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		securedMap = new HashMap<>();
		securedMap = (Map<String, String[]>) application.getAttribute("securedMap");
		
		// 보호 자원인지 여부 확인
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
		
		boolean pass = true;
		
		if(securedMap.containsKey(uri)) {
			MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
			String userRole = authMember.getMemRole();
			String[] resRoles = securedMap.get(uri);
			pass = Arrays.binarySearch(resRoles, userRole) >= 0;
		}
		
		if(pass) {
			chain.doFilter(request, response);
			
		}else {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
		
		// 아니면 통과, 자원이면 인가를 확인하기위해 ROLE 확인
		// 400번대 에러 (401)
		
	}

	@Override
	public void destroy() {
		logger.info("{} 소멸", getClass().getSimpleName());
		
	}
}
