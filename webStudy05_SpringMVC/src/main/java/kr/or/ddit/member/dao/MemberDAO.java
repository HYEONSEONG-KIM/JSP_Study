package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.pagingVO;

/**
 * 회원관리(CRUD)를 위한 Persistence Layer
 *
 */
@Mapper
public interface MemberDAO {
	public int insertMember(MemberVO member);
	
	/**
	 * 페이징 처리를 위해 total record 조회
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
	
	public MemberVO selectMemberDetail(String mem_id);
	/**
	 * 식별자(PK) 로 레코드 조회
	 * @param mem_id
	 * @param mem_pass TODO
	 * @return 존재하지 않는 경우, null 반환
	 */
	public MemberVO selectMemberById(String mem_id);
	public int updateMember(MemberVO member);
	public int deleteMember(String mem_id);
}
















