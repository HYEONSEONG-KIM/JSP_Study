package kr.or.ddit.exception;

import java.io.IOException;

import org.omg.CORBA.SystemException;

/**
 * 
 * 에러? 예외? : 예상하지 않았던 비정상 상황(Throwable)
 *  Error : 개발자가 처리하지 않는 비정상 => compile error
 *  Exception : 개발자가 처리할 수 있는 비정상 예외
 *  	checked exception(최상위 - Exception) : 예외가 던져지면(throw) 반드시 처리해야 하는 예외
 *  		- IOException, SQLException
 *  	unchecked exception(최상위 - RuntimeException) : 예외가 던져지고, 명시적인 예외 처리 코드가 없으면 자동으로 VM에게 제어가 전달되는 예외
 *  		- NullpointException
 *	
 *	** 예외 처리 방법
 *	1. 적극적인 처리
 *
 *		try{
 *			// 예외 발생 가능 코드
 *		}catch(Exception e -> try블럭 내에서 발생하는 예외 타입){
 *			// 예외 처리 코드(발생 예외를 변경 가능, 발생한 예회를 없애는 것도 가능)
 *		}finally{
 *			// 예외 발생 여부와 무관하게 처리하는 구분(자원의 해제)
 *		}
 *
 *	2. 호출자에게 전달 : throws
 *	
 *	** Custom exception 정의
 *		: 예외의 특성을 결정하고, 처리 정책에 따라 상위가 결정
 *		throw new CustomException();
 *
 *	--트랜잭션
 *	어떤 서비스를 수행할 때 각 단계(원자성)중 어느 한 단계라도 오류가 발생시 원상 복구가 되야 한다는 원리...
 *	
 */
public class ExceptionDesc {

	public static void main(String[] args) throws RuntimeException{
		try {
			test1();
		}catch (UserNotFoundExcpetion e) {
			System.err.println(e.getMessage());
			throw e;
		}
	}
	
	private static void test1(){
			try {
				if(1 == 1)
				throw new IOException("강제 발생 예외");
			} catch (IOException e) {
				throw new UserNotFoundExcpetion();
			}
	}
}



















