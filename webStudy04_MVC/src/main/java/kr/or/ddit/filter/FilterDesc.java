package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *	Decorating Filter Pattern
 *	: 요청과 응답에 대한 변형을 가하되 원본의 성질 자체를 바꾸지는 않는 부가 레이어 
 *
 *	1. Filter의 구현체 정의
 *		- 필터링 수행 callback( do Filter) : chain.doFilter를 통해 제어권을 적절히 이동
 *	2. WAS에 등록하고, Filter Chain이 형성되도록 함
 *		- filter chain 내에서 필터링 되는 순서는 등록된 순서를 따름
 *	3. 필터링할 요청에 대한 매핑 설정(경로 매핑, 확장자 매핑 사용)
 *
 */
// 어노테이션 버전에 영향
// 필터가 여러개 일시 순서가 결정되지 않음
//@WebFilter("/*")
public class FilterDesc implements Filter{

	private static final Logger logger = LoggerFactory.getLogger(FilterDesc.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 초기화", getClass().getSimpleName());
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		long start = System.currentTimeMillis();
		HttpServletRequest req = (HttpServletRequest) request;
		logger.info("{} 요청 발생", req.getRequestURI());
		chain.doFilter(request, response);
		long end = System.currentTimeMillis();
		logger.info("{} 처리 소요 시간 {}ms", req.getRequestURI(), (end-start));
	}

	@Override
	public void destroy() {
		logger.info("{} 소멸", getClass().getSimpleName());		
	}

}
