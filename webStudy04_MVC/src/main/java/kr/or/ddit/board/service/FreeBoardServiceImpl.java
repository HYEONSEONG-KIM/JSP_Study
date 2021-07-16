package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.FreeBoardDAO;
import kr.or.ddit.board.dao.FreeBoardDAOImpl;
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.pagingVO;

public class FreeBoardServiceImpl implements FreeBoardService {

	private FreeBoardServiceImpl() {}
	private static FreeBoardServiceImpl self;
	public static FreeBoardServiceImpl getInstance() {
		if(self == null) {
			self = new FreeBoardServiceImpl();
		}
		return self;
			
	}
	
	private FreeBoardDAO boardDAO = FreeBoardDAOImpl.getInstance();
	private AttatchDAO attDAO;
	
	@Override
	public ServiceResult createBoard(FreeBoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int retriveBoardCount(pagingVO<FreeBoardVO> paging) {
		return boardDAO.selectTotalRecord(paging);
	}

	@Override
	public List<FreeBoardVO> retriveBoardList(pagingVO<FreeBoardVO> paging) {
		return boardDAO.selectBoardList(paging);
	}

	@Override
	public FreeBoardVO retriveBoard(int boNo) {
		boardDAO.incrementHit(boNo);
		FreeBoardVO board = boardDAO.selectBoard(boNo);
		
		if(board == null) {
			throw new DataNotFoundException();
		}
		
		return board;
	}

	@Override
	public ServiceResult modifyBoard(FreeBoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeBoard(FreeBoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttatchVO download(int attNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult incrementCount(int boNo, CountType type) {
		
		ServiceResult result = null;
		int resultCnt = 0;
		if(type == CountType.RECOMMEND) {
			resultCnt = boardDAO.incrementRec(boNo);
		}else if(type == CountType.REPORT) {
			resultCnt = boardDAO.incrementRep(boNo);
		}
		
		if(resultCnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		
		return result;
	}

}
