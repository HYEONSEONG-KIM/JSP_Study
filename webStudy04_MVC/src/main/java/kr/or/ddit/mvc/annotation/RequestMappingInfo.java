package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;

public class RequestMappingInfo {
	// uri와 method정보 담는 클래스
	private RequestMappingCondition mappingCondition;
	// controller어노테이션을 사용하는 클래스들 중 한 클래스의 객체
	private Object commandHandler;
	// requestMapping 어노테이션 사용하는 메서드
	private Method handlerMethod;
	
	public RequestMappingInfo(RequestMappingCondition mappingCondition, Object commandHandler, Method handlerMethod) {
		super();
		this.mappingCondition = mappingCondition;
		this.commandHandler = commandHandler;
		this.handlerMethod = handlerMethod;
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

















