package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.pagingVO;

public interface FreeBoardService {
	/**
	 * 자유게시판 등록
	 * @param board
	 * @return OK ,FAIL
	 */
	public ServiceResult createBoard(FreeBoardVO board);
	
	/**
	 * 토탈 레코드 조회
	 * @param paging
	 * @return
	 */
	public int retriveBoardCount(pagingVO<FreeBoardVO> paging);
	
	/**
	 * 리스트 조회
	 * @param paging
	 * @return
	 */
	public List<FreeBoardVO> retriveBoardList(pagingVO<FreeBoardVO> paging);
	
	/**
	 * 
	 * @param boNo
	 * @return {@link DataNotFoundException}, OK, FAIL
	 */
	public FreeBoardVO retriveBoard(int boNo);
	
	/**
	 * 게시글 수정
	 * @param board
	 * @return {@link DataNotFoundException}, INVALIDPASS, OK, FAIL
	 */
	public ServiceResult modifyBoard(FreeBoardVO board);
	
	/**
	 * 게시글 삭제
	 * @param board
	 * @return {@link DataNotFoundException}, INVALIDPASS, OK, FAIL
	 */
	public ServiceResult removeBoard(FreeBoardVO board);
	
	/**
	 * 다운로드하기 위한 메서드
	 * @param attNo
	 * @return 다운로드하고자 하는 파일의 정보를 담은vo
	 */
	public AttatchVO download(int attNo);
	
	public static enum CountType{RECOMMEND, REPORT}
	
	/**
	 * 신고, 조회, 추천 증가
	 * @param boNo
	 * @param type
	 * @return OK FAIL
	 */
	public ServiceResult incrementCount(int boNo, CountType type);
}
