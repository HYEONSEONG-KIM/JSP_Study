package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.utils.ReflectionUtils;

public class RequestMappingHandlerMapping implements HandlerMapping {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestMappingHandlerMapping.class);

	// key => uri와 method, value => 수집된 핸들러 정보
	private Map<RequestMappingCondition, RequestMappingInfo> handlerMap;
	
	public RequestMappingHandlerMapping(String...basePackages) {
		handlerMap = new LinkedHashMap<>();
		initialize(basePackages);
	}
	
	private void initialize(String...basePackages) {
		// controller 어노테이션에 매핑된 정보를 알기위한 map
		// ReflectionUtils의 여러 패키지내의 클래스중 특정 어노테이션이 선언된 클래스 수집하는 메서드
		// => Controller 어노테이션이 포함된 클래스 수집
		Map<Class<?>, Controller> clzMap = 
				ReflectionUtils.getClassesWithAnnotationAtBasePackages(Controller.class, basePackages);
		// map안에 foreach로 반복 clz=> Controller가 포함된 클래스
		clzMap.forEach((clz, controller) -> {
			// Controller 어노테이션을 사용한 클래스가 어떤 타입인지 모르므로 object
			Object commandHandler;
			try {
				// newInstance 사용하면 new와 인스턴스 한번에 생성-> 객체생성
				// Controller를 사용한 클래스 중 하나의 객체 생성
				commandHandler = clz.newInstance();
				// RequestMapping 어노테이션에 매핑된 정보를 알기위한 map
				// Controller를 사용항 클래스중 RequestMapping 어노테이션을 사용한 메서드를 수집
				Map<Method, RequestMapping> mtdMap = 
						ReflectionUtils.getMethodsWithAnnotationAtClass(clz, RequestMapping.class, String.class);
				
				// handlerMethod=> requestMapping 어노테이션 사용하는 메서드
				mtdMap.forEach((handlerMethod, requestMapping)->{
					// requestMapping어노테이션 value는 uri(ex=> member/memberInsert.do)
					String uri = requestMapping.value();
					// requestMapping method는 이동방식(do, post...)
					RequestMethod method =  requestMapping.method();
					// RequestMappingCondition =>uri와 method 정보를 담고있는 클래스
					RequestMappingCondition mappingCondition = new RequestMappingCondition(uri, method);
					// 매핑해야할 정보를 담고있는 클래스
					RequestMappingInfo mappingInfo = new RequestMappingInfo(mappingCondition, commandHandler, handlerMethod);
					// 같은 요청인데 같은 uri일 경우 exception처리
					if(handlerMap.containsKey(mappingCondition)) {
						RequestMappingInfo already =  handlerMap.get(mappingCondition);
						throw new RuntimeException(String.format(
									"%s 요청에 대한 핸들러가 중복 적용됨, \n %s.%s - %s.%s",
									mappingCondition , 
									already.getCommandHandler().getClass().getName(),
									already.getHandlerMethod().getName(),
									mappingInfo.getCommandHandler().getClass().getName(),
									mappingInfo.getHandlerMethod().getName()
									
								));
					}else {
						handlerMap.put(mappingCondition, mappingInfo);
						logger.info("수집된 핸들러 정보, {}", mappingInfo);
					}
				});
				
			} catch (InstantiationException | IllegalAccessException e) {
				logger.error(e.getMessage(), e);
			}
			
	
			
		});
	}

	// command핸들러를 찾기위함
	@Override
	public RequestMappingInfo findCommandHandler(HttpServletRequest req) {
		// req요청으로 들어온 uri의 매핑주소가 없으면 null반환
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length()).split(",")[0];
		RequestMethod method =  RequestMethod.valueOf(req.getMethod().toUpperCase());
		RequestMappingCondition mappingCondition = new RequestMappingCondition(uri, method);
		
		return handlerMap.get(mappingCondition);
	}

}
