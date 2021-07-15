package kr.or.ddit.mvc.resolvers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 파라미터의 다형성을 지원하기위한 어노테이션
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
	
	String value();
	// 필수파라미터 인지 아닌지
	boolean required() default true;
	String defaultValue() default "";
}
