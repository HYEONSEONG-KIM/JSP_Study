package kr.or.ddit.mvc.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *	{@link HttpServletRequest} 
 * 	{@link HttpServletResponse}
 * 	{@link HttpSession}
 *	세가지 타입의 파라미터를 처리하기 위한 전략객체
 */
public class ServletSpecArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		
		return HttpServletRequest.class.equals(parameterType)
				|| HttpServletResponse.class.equals(parameterType)
				|| HttpSession.class.equals(parameterType);
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		Object parameterValue = null;
		if(HttpServletRequest.class.equals(parameterType)) {
			parameterValue = req;
		}else if(HttpServletResponse.class.equals(parameterType)) {
			parameterValue = resp;
		}else {
			parameterValue = req.getSession();
		}
		
		return parameterValue;
	}

}
