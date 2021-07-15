package kr.or.ddit.mvc.annotation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *	Front로 부터 전달받은 핸들러에 대한 정보를 바탕으로, 
 *	command handler를 호출하는 역할 수행 
 *
 */
public interface HandlerAdapter {
	
	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException;
}
