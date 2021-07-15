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
		Map<Class<?>, Controller> clzMap = 
				ReflectionUtils.getClassesWithAnnotationAtBasePackages(Controller.class, basePackages);
		clzMap.forEach((clz, controller) -> {
			Object commandHandler;
			try {
				commandHandler = clz.newInstance();
				// RequestMapping 어노테이션에 매핑된 정보를 알기위한 map
				Map<Method, RequestMapping> mtdMap = 
						ReflectionUtils.getMethodsWithAnnotationAtClass(clz, RequestMapping.class, String.class);
				
				mtdMap.forEach((handlerMethod, requestMapping)->{
					String uri = requestMapping.value();
					RequestMethod method =  requestMapping.method();
					RequestMappingCondition mappingCondition = new RequestMappingCondition(uri, method);
					RequestMappingInfo mappingInfo = new RequestMappingInfo(mappingCondition, commandHandler, handlerMethod);
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
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length()).split(",")[0];
		RequestMethod method =  RequestMethod.valueOf(req.getMethod().toUpperCase());
		RequestMappingCondition mappingCondition = new RequestMappingCondition(uri, method);
		
		return handlerMap.get(mappingCondition);
	}

}
