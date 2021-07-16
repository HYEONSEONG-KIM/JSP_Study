package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.resolvers.BadRequestException;
import kr.or.ddit.mvc.resolvers.HandlerMethodArgumentResolver;
import kr.or.ddit.mvc.resolvers.ModelAttributeArgumentResolver;
import kr.or.ddit.mvc.resolvers.RequestParamArgumentResolver;
import kr.or.ddit.mvc.resolvers.ServletSpecArgumentResolver;

public class RequestMappingHandlerAdapter implements HandlerAdapter {
	
	private List<HandlerMethodArgumentResolver> argumentResolvers;
	
	public RequestMappingHandlerAdapter() {
		argumentResolvers = new ArrayList<>();
		argumentResolvers.add(new ServletSpecArgumentResolver());
		argumentResolvers.add(new RequestParamArgumentResolver());
		argumentResolvers.add(new ModelAttributeArgumentResolver());
	}
	
	// 확장형(?) 기능 제공(?)
	public void addArgumentResolvers(HandlerMethodArgumentResolver...resolvers) {
		argumentResolvers.addAll(Arrays.asList(resolvers));
	}
	
	private HandlerMethodArgumentResolver findArgumentResolver(Parameter parameter) {
		HandlerMethodArgumentResolver finded = null;
		//argumentResolvers 위 생성자에서 각각 파라미터를 처리하는 resolver 클래스를 담음
		for(HandlerMethodArgumentResolver tmp : argumentResolvers) {
			// 각 resolver클래스의 isSupported메서드를 통해 해당 resolver에 해당하는 파라미터 타입인지 체크
			if(tmp.isSupported(parameter)) {
				finded = tmp;
				break;
			}
		}
		return finded;
	}
	
	@Override
	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// getCommandHandler => mapping 대상인 클래스
		Object commandHandler = mappingInfo.getCommandHandler();
		// 그 클래스안에 메서드
		Method handlerMethod = mappingInfo.getHandlerMethod();
		// 그 메서드안에 파라미터의 갯수 반환
		int parameterCount = handlerMethod.getParameterCount();
		// 파라미터의 타입이 무엇인지 모르니 object
		Object[] parameterValues = null;
		try {
			// 0이상이면 파라미터가 하나이상 존재
			if(parameterCount > 0) {
				 parameterValues = new Object[parameterCount];
				 Parameter[] parameters = handlerMethod.getParameters();
				 for(int idx = 0; idx < parameterCount; idx++) {
					 // 메서드 파라미터를 하나씩 담아 파라미터 어노테이션
					 Parameter single = parameters[idx];
					 // 위 findArgumentResolver를 통해 현재 single에 해당하는 파라미터에 해당하는 resolver를 담음
					 HandlerMethodArgumentResolver resolver = findArgumentResolver(single);
					 // 아무것도 담기지 않으면 어느 타입에도 해당하는 파라미터가 아니므로 처리불가
					 if(resolver == null) {
						 throw new ServletException(
								String.format("%s 타입의 핸들러 메서드 아규먼트는 처리 할 수 있는 resolver가 없음", single.getType().getName())
							);
					 }
					 // 해당 배열에는 파라미터로 들어온 value값 저장
					 parameterValues[idx] = resolver.argumentResolve(single, req, resp);
				 }
			}
			// C.H에게 파라미터의 value값을 전달하여 해당 C.H에 리턴값으로 logicl viewName 리턴
			// FrontController에게 viewName전달
			return (String)handlerMethod.invoke(commandHandler, parameterValues);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ServletException(e);
		} catch (BadRequestException e) {
			resp.sendError(400,e.getMessage());
			return null;
		}
	}

}
