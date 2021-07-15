package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;

/**
 * 	1. 핸들러에 대한 정보를 수집하고, handler map을 형성
 *	2. 수집된 정보를 기반으로 요청을 처리할 수 있는 handler 검색
 */
public interface HandlerMapping {
	public RequestMappingInfo findCommandHandler(HttpServletRequest req);
}
