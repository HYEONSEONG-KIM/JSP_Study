package kr.or.ddit.validate.contraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD,METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy = FileMimeValidator.class)
public @interface FileMime {
	
	String message() default "{kr.or.ddit.validate.contraints.FileMime.message}";
	
	// 여러개의 그룹을 사용하기위해 배열형태로
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default { };
	
	String mime();
}
