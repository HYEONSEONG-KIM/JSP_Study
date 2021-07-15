package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;

public class RequestMappingInfo {
	private RequestMappingCondition mappingCondition;
	private Object commandHandler;
	private Method handlerMethod;
	
	public RequestMappingInfo(RequestMappingCondition mappingCondition, Object commandHandler, Method method) {
		super();
		this.mappingCondition = mappingCondition;
		this.commandHandler = commandHandler;
		this.handlerMethod = method;
	}

	public RequestMappingCondition getMappingCondition() {
		return mappingCondition;
	}

	public Object getCommandHandler() {
		return commandHandler;
	}

	public Method getHandlerMethod() {
		return handlerMethod;
	}

	@Override
	public String toString() {
		return String.format("%s - %s.%s(%s),", mappingCondition, 
					commandHandler.getClass().getName(),
					handlerMethod.getName(),
					Arrays.toString(handlerMethod.getParameterTypes())
				);
		
	}
	
	
	
}

















