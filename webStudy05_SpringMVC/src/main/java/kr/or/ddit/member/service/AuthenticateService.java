package kr.or.ddit.member.service;

import kr.or.ddit.commons.UserNotFoundExcpetion;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.MemberVO;

/**
 *	인증 처리를 위한 Business Logic Layer 
 *
 */
public interface AuthenticateService {
	
	/**
	 * 
	 * 
	 * @param param
	 * @return 인증성공 : {@link MemberVO}, 비밀번호 오류 : {@link ServiceResult}.INVALIDPASSWORD
	 * 		존재하지 않으면, {@link UserNotFoundExcpetion} 발생
	 */
	public Object authenticate(MemberVO param);
}
