package kr.or.ddit.commons;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *	인증처리 하는 과정에서 사용자가 존재하지 않을때 발생시킬 예외 
 * 
 */
@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class UserNotFoundExcpetion extends RuntimeException{

	public UserNotFoundExcpetion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundExcpetion(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundExcpetion(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundExcpetion(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundExcpetion(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
