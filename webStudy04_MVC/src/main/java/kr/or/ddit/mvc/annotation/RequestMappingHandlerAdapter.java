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
		for(HandlerMethodArgumentResolver tmp : argumentResolvers) {
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
		Object commandHandler = mappingInfo.getCommandHandler();
		Method handlerMethod = mappingInfo.getHandlerMethod();
		int parameterCount = handlerMethod.getParameterCount();
		Object[] parameterValues = null;
		try {
			if(parameterCount > 0) {
				 parameterValues = new Object[parameterCount];
				 Parameter[] parameters = handlerMethod.getParameters();
				 for(int idx = 0; idx < parameterCount; idx++) {
					 Parameter single = parameters[idx];
					 
					 HandlerMethodArgumentResolver resolver = findArgumentResolver(single);
					 if(resolver == null) {
						 throw new ServletException(
								String.format("%s 타입의 핸들러 메서드 아규먼트는 처리 할 수 있는 resolver가 없음", single.getType().getName())
							);
					 }
					 parameterValues[idx] = resolver.argumentResolve(single, req, resp);
				 }
			}
			return (String)handlerMethod.invoke(commandHandler, parameterValues);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ServletException(e);
		} catch (BadRequestException e) {
			resp.sendError(400,e.getMessage());
			return null;
		}
	}

}
