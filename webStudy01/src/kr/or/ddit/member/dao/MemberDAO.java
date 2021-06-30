package kr.or.ddit.member.dao;

import kr.or.ddit.vo.MemberVO;

/**
 *	회원관리(CRUD)를 위한 persistence Layer 
 * 
 *
 */
public interface MemberDAO {

	/**
	 * 식별자(PK)로 레코드 조회
	 * @param mem_id
	 * @return 존재하지 않는 경우, null 반환
	 */
	public MemberVO selectMemberById(String mem_id);
}
