package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.FreeReplyVO;
import kr.or.ddit.vo.pagingVO;

public interface FreeReplyService {
	
	/**
	 * 댓글 리스트 조회
	 * @param boNo
	 * @return
	 */
	public void retrieveReply(pagingVO<FreeReplyVO> paging, int boNo);
	/**
	 * 댓글 등록
	 * @param reply
	 * @return OK, FAIL
	 */
	public ServiceResult createReply(FreeReplyVO reply);
	
	/**
	 * 댓글 수정
	 * @param reply
	 * @return OK, FAIL, INVALIDPASS, {@link DataNotFoundException}
	 */
	public ServiceResult modifyReply(FreeReplyVO reply);
	
	/**
	 * 댓글 삭제
	 * @param repNo
	 * @return OK, FAIL, INVALIDPASS, {@link DataNotFoundException}
	 */
	public ServiceResult removeReply(FreeReplyVO reply);
	
}
