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

/**
 *	접근 제어를 통한 서버 보안
 *	인증(Authentication) + 인가(Authorization)
 *	
 *	# 인증 => 보호 자원에 대한 요청을 발생시킨 사용자의 신원을 확인하는 과정
 *	# 인가 => 보호 자원에 대한 요청을 발생시킨 사용자가 해당 자원에 대해 접근이 허가 되어있는지 확인하는 과정
 *
 */
public class AuthenticationFilter implements Filter{

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
	private Map<String, String[]> securedMap;
	private ServletContext application;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 초기화", getClass().getSimpleName());
		securedMap = new HashMap<String, String[]>();
		Properties props = new Properties();
		String path = filterConfig.getInitParameter("secured_path");
		
		application = filterConfig.getServletContext();
		
		try(
			InputStream is = getClass().getResourceAsStream(path);
		) {
			props.loadFromXML(is);
			for(Object key : props.keySet()) {
				Object value = props.get(key);
				String resourceUrl = key.toString().trim();
				String[] roles = value.toString().trim().split(",");
				// 이진트리를 사용하기 위해 정렬 -> 트리구조=> 정렬 순서
				Arrays.sort(roles);
				securedMap.put(resourceUrl, roles);
				logger.info("{} : {}", resourceUrl, Arrays.toString(roles));
				
			}
			application.setAttribute("securedMap", securedMap);
			
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 1. 현재 요청이 어떤 자원을 요구?? 
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String loginForm = "/login/loginForm.jsp" ;
		
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
		
		

		boolean pass = uri.equals(loginForm) || !securedMap.containsKey(uri);
		
		logger.info("{}", uri);
		// 2. 그 자원이 보호 자원인지 여부 확인
		
		
		// 2-1. 보호자원이 아니면 통과, 보호 자원이면 필터
		String message = "";
		
		if(!pass) {
			// 3. 로그인이 안되있음 통과x
			MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
			if(authMember == null) {
				req.getSession().setAttribute("savedRequestURL", uri);
				message = "로그인해야 가능한 서비스 입니다";
				req.getSession().setAttribute("message", message);
				resp.sendRedirect(req.getContextPath() + loginForm);
			}else {
				chain.doFilter(request, response);
			}
			
		}else {
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
