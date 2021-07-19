package kr.or.ddit.board.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;

/**
 *	Attatch 테이블을 대상으로 한 Persistence Layer 
 *
 */
public interface AttatchDAO {
	public int insertAttatchs(FreeBoardVO board,SqlSession sqlSession);
	/**
	 * 파일을 다운로드 하는경우 select
	 * @param attNo
	 * @return
	 */
	public AttatchVO selectAttatch(int attNo, SqlSession sqlSession );
	/**
	 * 글을 수정시 파일을 변경시 기존 파일 삭제하고 다시 업로드 하기위한 메서드
	 * @param board
	 */
	public int deleteAttatchs(FreeBoardVO board, SqlSession sqlSession);
	/**
	 * 게시판 삭제시 해당 게시판의 파일들을 먼저 삭제하기 위한 메서드
	 * @param boNo
	 * @return
	 */
	public int deleteAll(int boNo);
	
	/**
	 * 다운로드 횟수 증가
	 * @param attNo
	 * @return
	 */
	public int incrementDownCount(int attNo);
}
