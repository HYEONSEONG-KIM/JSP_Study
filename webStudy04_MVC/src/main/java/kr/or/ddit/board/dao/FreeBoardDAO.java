package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.pagingVO;

/**
 *	FreeBoard 테이블 대상으로 한 Persistence Layer 
 *
 */
public interface FreeBoardDAO {

	public int insertBoard(FreeBoardVO board, SqlSession sqlSession);
	
	public int selectTotalRecord(pagingVO<FreeBoardVO> paging);
	public List<FreeBoardVO> selectBoardList(pagingVO<FreeBoardVO> paging);
	public FreeBoardVO selectBoard(int boNo);
	public int updateBoard(FreeBoardVO board, SqlSession sqlSession);
	public int deleteBoard(int boNo, SqlSession sqlSession);

	/**
	 * 조회수 증가를 위한 메서드
	 * @param boNo
	 * @return
	 */
	public int incrementHit(int boNo);
	/**
	 * 추천수 증가를 위한 메서드
	 * @param boNo
	 * @return
	 */
	public int incrementRec(int boNo);
	/**
	 * 신고수 증가를 위한 메서드
	 * @param boNo
	 * @return
	 */
	public int incrementRep(int boNo);
	

}
