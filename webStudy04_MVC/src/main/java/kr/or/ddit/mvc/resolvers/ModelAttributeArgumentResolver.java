package kr.or.ddit.mvc.resolvers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ClassUtils;

/**
 *	@ModelAttribute 어노테이션을 가진 핸들러 메서드 아규먼트(command Object)에 대한 처리 
 *
 */
public class ModelAttributeArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		ModelAttribute annotation =  parameter.getAnnotation(ModelAttribute.class);
		return annotation != null 
				&& !(
						String.class.equals(parameterType)
						|| ClassUtils.isPrimitiveOrWrapper(parameterType)
				
				);
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		ModelAttribute annotation =  parameter.getAnnotation(ModelAttribute.class);
		
		Object commandObject;
		try {
			String attrName = annotation.value();
			commandObject = parameterType.newInstance();
			req.setAttribute(attrName, commandObject);
			
			BeanUtils.populate(commandObject, req.getParameterMap());
			
			return commandObject;
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		
	}

}














