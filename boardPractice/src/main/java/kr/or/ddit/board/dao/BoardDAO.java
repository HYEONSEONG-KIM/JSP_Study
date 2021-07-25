package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
import kr.or.ddit.commons.exception.DataNotFoundException;

@Mapper
public interface BoardDAO {

	/**
	 * 게시판의 전체 레코드 수 조회
	 * @param paging
	 * @return 레코드 수
	 */
	public int totalRecord(PagingVO<BoardVO> paging);
	
	/**
	 * 게시판 리스트 조회 및 페이징, 검색
	 * @param paging
	 * @return 결과에 해당하는 게시판 리스트
	 */
	public List<BoardVO> boardList(PagingVO<BoardVO> paging);
	
	/**
	 * 게시판 상세 조회
	 * @param boNo
	 * @return 조회 하고자하는 게시글
	 */
	public BoardVO boardSelect(int boNo);
	
	/**
	 * 게시글 수정
	 * @param board
	 * @return OK, FAIL, {@link DataNotFoundException}
	 */
	public int boardUpdate(BoardVO board);
	
	/**
	 * 게시글 삭제
	 * @param boNo
	 * @return OK, FAIL, {@link DataNotFoundException}
	 */
	public int boardDelete(int boNo);
	
	/**
	 * 조회수 증가
	 * @param boNo
	 * @return OK, FAIL
	 */
	public int incrementHit(int boNo);

	/**
	 * 신고수 증가
	 * @param boNo
	 * @return OK, FAIL
	 */
	public int incrementRep(int boNo);
	
	/**
	 * 추천수 증가
	 * @param boNo
	 * @return OK, FAIL
	 */
	public int incrementRec(int boNo);
	
}
