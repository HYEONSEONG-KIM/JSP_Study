package kr.or.ddit.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;

public class ValidatorUtils<T> {
	
	private static Validator validator;
	
	
	static {
		// ResourceBundle을 사용하여 메시지를 출력할 properties 파일을 불러오기
		  ValidatorFactory factory =Validation.byDefaultProvider()
			        .configure()
			        .messageInterpolator(
			                new ResourceBundleMessageInterpolator(
			                        new PlatformResourceBundleLocator( "kr.or.ddit.msgs.errorMesaage" )
			                )
			        )
			        .buildValidatorFactory();

	      validator = factory.getValidator();
	}
	
	public boolean validate(T target, Map<String, List<String>> errors, Class<?>...groups) {
		// validator에서 제약 조건 위반한 필드와 위반메세지를 set으로 리턴
		Set<ConstraintViolation<T>> violations = validator.validate(target, groups);
		
		for(ConstraintViolation<T> singleViolation : violations) {
			// getPropertyPath => 필드명
			String key = singleViolation.getPropertyPath().toString();
			// getMessage => 메세지 출력
			String message = singleViolation.getMessage();
			// 하나의 필드에 여러가지 제약 조건이 담길 수 있으므로 message는 List로 하여 여러개를 저장
			List<String> messages = errors.get(key);
			if(messages == null) {
				messages = new ArrayList<String>();
				errors.put(key, messages);
			}
			messages.add(message);
			
		}
		return violations.size() == 0;
	}
}










