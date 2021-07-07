package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ZipVO;
import kr.or.ddit.vo.pagingVO;

/**
 *	회원관리(CRUD)를 위한 persistence Layer 
 * 
 *
 */
public interface MemberDAO {

	public int insertMember(MemberVO member);
	/**
	 * 페이징 처리를 위해 total record조회
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalRecord(pagingVO pagingVO);
	/**
	 * 페이징 처리를 위해 구간별 데이터를 조회
	 * @param pagingVO
	 * @return
	 */
	public List<MemberVO> selectMemberList(pagingVO pagingVO);
	public MemberVO selectMemberDatail(String mem_id);
	
	
	/**
	 * 식별자(PK)로 레코드 조회
	 * @param mem_id
	 * @return 존재하지 않는 경우, null 반환
	 */
	public MemberVO selectMemberById(String mem_id);
	public int updateMember(MemberVO member);
	public int deleteMember(String mem_id);

	
	public List<ZipVO> selectZip();
}















