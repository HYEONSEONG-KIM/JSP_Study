package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
import kr.or.ddit.enumtype.ServiceResult;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO boardDao;
	
	@Override
	public void retreiveBoardList(PagingVO<BoardVO> paging) {
		
		int boardRecord = boardDao.totalRecord(paging);
		paging.setTotalRecord(boardRecord);
		
		List<BoardVO> boardList = boardDao.boardList(paging);
		paging.setDataList(boardList);
		
	}

	@Override
	public BoardVO retreiveBoardSelect(int boNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult increment(int boBo) {
		// TODO Auto-generated method stub
		return null;
	}

}
