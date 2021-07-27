package kr.or.ddit.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

/**
 * 분리된 관심사에 따라 Cross Cutting Concern을 코드화 시킴
 * 차후에 런타임에 기 개발된 target들을 대상으로 weaving 하게 됨
 * @author PC
 *
 */
@Slf4j
@Aspect
public class LoggingAdvice {
	
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {}
	
	@Around("dummy()")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		// before
		long start = System.currentTimeMillis();
		Object target = joinPoint.getTarget();
		// 메서드의 모든 정보 담김
		Signature signature = joinPoint.getSignature();
		
		String targetName = String.format("%s.%s", target.getClass().getName(), signature.getName());
		
		// 타겟의 파라미터
		Object[] args = joinPoint.getArgs();
		log.info("타겟-{}({})-호출 전 위빙",targetName, Arrays.toString(args));
		
		
		Object retValue = joinPoint.proceed(args);
		long end = System.currentTimeMillis();
		log.info("타겟-{}- 호출 후 위빙,소요시간 : {}ms, \n반환값 : {}",targetName,(end-start) ,retValue);
		return retValue;
	}
}








