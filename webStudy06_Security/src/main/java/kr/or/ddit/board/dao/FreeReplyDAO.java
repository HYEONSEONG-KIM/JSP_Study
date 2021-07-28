package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.vo.FreeReplyVO;
import kr.or.ddit.vo.pagingVO;

@Mapper
public interface FreeReplyDAO {
	
	/**
	 * 게시글에 해당하는 댓글 레코드 조회
	 * @param boNo
	 * @return
	 */
	public int totalReplyRecored(int boNo);

	/**
	 * 게시글 댓글 리스트 조회
	 * @param boNo
	 * @return
	 */
	public List<FreeReplyVO> boardReplyList(pagingVO<FreeReplyVO> paging);
	
	/**
	 * 게시글 패스워드 조회
	 * @param repNo
	 * @return
	 */
	public FreeReplyVO selectReply(int repNo);
	/**
	 * 게시글 댓글 등록
	 * @param reply
	 * @return
	 */
	public int insertBoardReply(FreeReplyVO reply);
	
	/**
	 * 댓글 수정
	 * @param reply
	 * @return OK, FAIL, {@link DataNotFoundException}, INVALIDPASS
	 */
	public int updateBoardReply(FreeReplyVO reply);
	
	/**
	 * 댓글 삭제
	 * @param repNo
	 * @return OK, FAIL, {@link DataNotFoundException}, INVALIDPASS
	 */
	public int deleteBoardReply(int repNo);
}




