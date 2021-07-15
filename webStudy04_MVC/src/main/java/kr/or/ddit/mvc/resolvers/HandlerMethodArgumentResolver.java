package kr.or.ddit.mvc.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *	핸들러 메서드 호출시에 전달한 인자(파라미터) 하나에 대한 처리를 위한 객체 
 *
 */
// 핸들러 어댑터가 사용
public interface HandlerMethodArgumentResolver {
	public boolean isSupported(Parameter parameter);
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException,IOException;
}
