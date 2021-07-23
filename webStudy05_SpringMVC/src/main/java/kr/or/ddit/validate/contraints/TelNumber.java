package kr.or.ddit.validate.contraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.groups.Default;

/**
 *	전화번호 형식 확인을 위한 검증 어노테이션 
 *
 */
// 어디에 적용할것인가
@Target(FIELD)
// 언제까지 살려 놓을 것인가
@Retention(RUNTIME)
// 어디서 검증작업이 이루어지나
@Constraint(validatedBy = TelNumberValidator.class)
public @interface TelNumber {
	
	String message() default "{kr.or.ddit.validate.contraints.TelNumber.message}"; 
	// {해당 클래스 QN.message} -> properties 파일에서 메세지 작성
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default { };
	
	String regexp() default "\\d{2,4}-\\d{3,4}-\\d{4}";
	String placeholder() default "000-0000-0000";

}
