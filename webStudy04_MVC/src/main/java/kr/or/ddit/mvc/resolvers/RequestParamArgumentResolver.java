package kr.or.ddit.mvc.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ClassUtils;

/**
 *	기본형이나 wrapper 혹은 String 타입의 한건의 요청 파라미터를 받기 위한 핸들러 메서드 아규먼트에 대한 처리
 *	@RequestParam 어노테이션을 가진 파라미터에 대한 처리 
 *
 */
public class RequestParamArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		Class<?> parameterType =  parameter.getType();
		RequestParam annotation =  parameter.getAnnotation(RequestParam.class);
		return annotation != null 
				&&(
						String.class.equals(parameterType)
						|| ClassUtils.isPrimitiveOrWrapper(parameterType)
						
				) ;
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType =  parameter.getType();
		RequestParam annotation =  parameter.getAnnotation(RequestParam.class);
		String reqParamName = annotation.value();
		boolean required = annotation.required();
		String defaultValue = annotation.defaultValue();
		
		String reqParamValue = req.getParameter(reqParamName);
		if(reqParamValue==null && required) {
			// 400
			throw new BadRequestException(String.format("%s 타입의 필수파라미터 누락", parameterType.getName()));
		}else if(reqParamValue==null || reqParamValue.isEmpty()) {
			reqParamValue = defaultValue;
		}
		
		
		Object parameterValue = null;
		try {
			if(Byte.class.equals(parameterType) || byte.class.equals(parameterType)) {
				parameterValue = Byte.parseByte(reqParamValue);
			}else if(Short.class.equals(parameterType) || short.class.equals(parameterType)) {
				parameterValue = Short.parseShort(reqParamValue);
			}else if(Integer.class.equals(parameterType) || int.class.equals(parameterType)) {
				parameterValue = Integer.parseInt(reqParamValue);
			}else if(Long.class.equals(parameterType) || long.class.equals(parameterType)) {
				parameterValue = Long.parseLong(reqParamValue);
			}else if(Float.class.equals(parameterType) || float.class.equals(parameterType)) {
				parameterValue = Float.parseFloat(reqParamValue);
			}else if(Double.class.equals(parameterType) || double.class.equals(parameterType)) {
				parameterValue = Double.parseDouble(reqParamValue);
			}else if(Boolean.class.equals(parameterType) || boolean.class.equals(parameterType)) {
				parameterValue = Boolean.parseBoolean(reqParamValue);
			}else if(Character.class.equals(parameterType) || char.class.equals(parameterType)) {
				parameterValue = reqParamValue.charAt(0);
			}else {
				parameterValue = reqParamValue;
			}
			
			return parameterValue;
		}catch (Exception e) {
			throw new BadRequestException(e);
		}
	}

}








