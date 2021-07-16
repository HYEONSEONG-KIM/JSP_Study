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
	/**
	 * 인자로 들어온 parameter가 해당 클래스의 resolver인지 체크
	 * @param parameter
	 * @return
	 */
	public boolean isSupported(Parameter parameter);
	/**
	 * 파라미터가 해당 resolver에 속하면 처리하는 메서드
	 * @param parameter
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException,IOException;
}
