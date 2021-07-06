package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.commons.UserNotFoundExcpetion;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.pagingVO;

/**
 *	회원 관리(CRUD) Business Logic Layer 
 *
 */
public interface MemberService {
	
	/**
	 * 
	 * @param member
	 * @return PKDUPLICATED, OK, FAIL
	 */
	public ServiceResult creatMember(MemberVO member);
	
	public int retrieveMemberCount(pagingVO pagingvo);
	
	public List<MemberVO> retrieveMemberList(pagingVO pagingVO);

	/**
	 * 
	 * @param mem_id
	 * @return 존재하지 않는 경우, {@link UserNotFoundExcpetion} 발생
	 */
	public MemberVO retrieveMember(String mem_id);
	
	/**
	 * 
	 * @param member
	 * @return 존재하지 않는 경우, {@link UserNotFoundExcpetion} 발생
	 * 			INVALIDPASSWORD, OK, FAIL
	 * 
	 */
	public ServiceResult modifyMember(MemberVO member);
	
	/**
	 * 
	 * @param member
	 * @return 존재하지 않는 경우, {@link UserNotFoundExcpetion} 발생
	 * 			INVALIDPASSWORD, OK, FAIL
	 * 
	 */
	public ServiceResult removeMember(MemberVO member);
	
	
}
