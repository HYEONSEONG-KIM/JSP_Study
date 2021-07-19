package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.taglibs.standard.lang.jstl.MultiplyOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.AttatchDAOImpl;
import kr.or.ddit.board.dao.FreeBoardDAO;
import kr.or.ddit.board.dao.FreeBoardDAOImpl;
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.utils.EncryptUtils;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.pagingVO;

public class FreeBoardServiceImpl implements FreeBoardService {

	private static final Logger logger = LoggerFactory.getLogger(FreeBoardServiceImpl.class);
	private FreeBoardServiceImpl() {}
	private static FreeBoardServiceImpl self;
	public static FreeBoardServiceImpl getInstance() {
		if(self == null) {
			self = new FreeBoardServiceImpl();
		}
		return self;
			
	}
	
	private FreeBoardDAO boardDAO = FreeBoardDAOImpl.getInstance();
	private AttatchDAO attDAO = AttatchDAOImpl.getInstance();
	private EncryptUtils utils = new EncryptUtils();
	
	SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	private File saveFolder = new File("C:/saveFiles");
	
	
	private int proccessboardImage(FreeBoardVO board,SqlSession sqlSession) {
		
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList == null || attatchList.isEmpty()) return 0;
		
		int rowcnt = attDAO.insertAttatchs(board,sqlSession);
		
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		try {
			for(AttatchVO attatch : attatchList) {
				attatch.saveToBinary(saveFolder);
			}
			return rowcnt;
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private int deleteBoardImage(FreeBoardVO board, SqlSession sqlSession) {
		
		int[] delAttrNos = board.getDelAttrNos();
		if(delAttrNos.length == 0) return 0;
		int rowCnt = 0;
		try {
			for(int i = 0; i < delAttrNos.length; i ++) {
				AttatchVO attatch = attDAO.selectAttatch(delAttrNos[i], sqlSession);
				attatch.deleteToBinary(saveFolder);
				System.out.println(delAttrNos[i]);
				System.out.println(attatch);
			}
			rowCnt = attDAO.deleteAttatchs(board, sqlSession);
			return rowCnt;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	@Override
	public ServiceResult createBoard(FreeBoardVO board) {
		
		ServiceResult result = null;
		
		String plain = board.getBoPass();
		
	
		String encryted = utils.encryptSha512Base64(plain);
		
		board.setBoPass(encryted);
		
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			int boardResult = boardDAO.insertBoard(board, sqlSession);
			if(boardResult > 0) {
				int uplaodResult = proccessboardImage(board, sqlSession);
				
					sqlSession.commit();
					result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		
		}
		return result;
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

		ServiceResult result = null;
		
		FreeBoardVO saveBoard = boardDAO.selectBoard(board.getBoNo());
		if(saveBoard == null) {
			throw new DataNotFoundException();
		}
		
		String inputPass = board.getBoPass();
		String encryptPass =utils.encryptSha512Base64(inputPass);
		
		if(!encryptPass.equals(saveBoard.getBoPass())){
			result = ServiceResult.INVALIDPASSWORD;
			return result;
		}
		
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			int rowcnt = boardDAO.updateBoard(board, sqlSession);
			
			if(rowcnt > 0) {
				rowcnt += proccessboardImage(board,sqlSession);
				deleteBoardImage(board,sqlSession);
				sqlSession.commit();
				
				result = ServiceResult.OK;
				
			}else {
				result = ServiceResult.FAIL;
			}
			
		}
		
		
		
		return result;
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
