package kr.or.ddit.board.service;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;

public interface BoardService {

	/**
	 * 게시글 리스트 조회(call by reference)
	 * @param paging
	 */
	public void retreiveBoardList(PagingVO<BoardVO> paging);
	
	/**
	 * 게시글 상세 조회
	 * @param boNo
	 * @return
	 */
	public BoardVO retreiveBoardSelect(int boNo);
	
	/**
	 * 게시글 수정
	 * @param board
	 * @return OK, FAIL, {@link DataNotFoundException}, INVALIDPASS
	 */
	public ServiceResult modifyBoard(BoardVO board);
	
	/**
	 * 게시글 삭제
	 * @param board
	 * @return OK, FAIL, INVALIDPASS, {@link DataNotFoundException}
	 */
	public ServiceResult removeBoard(BoardVO board);
	
	/**
	 * 조회수 신고수 추천수 증가
	 * @param boBo
	 * @return OK, FAIL
	 */
	public ServiceResult increment(int boBo);
	
}
