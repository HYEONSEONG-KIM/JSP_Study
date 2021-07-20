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
	
	private int deleteBoardAllFiles(FreeBoardVO saveBoard, SqlSession sqlSession) {
		
		List<AttatchVO> attatchList = saveBoard.getAttatchList();
		if(attatchList == null || attatchList.isEmpty()) return 0;
		
		int cnt = 0;
		
		for(AttatchVO attatch : attatchList) {
			attatch.deleteToBinary(saveFolder);
			cnt++;
		}
		
		return cnt;
	}

	
	
	private int deleteBoardFile(FreeBoardVO board, SqlSession sqlSession) {
		
		int[] delAttrNos = board.getDelAttrNos();
		if(delAttrNos == null) return 0;
		int rowCnt = 0;
		List<AttatchVO> attatchList = new ArrayList<>();
		try {
			for(int i = 0; i < delAttrNos.length; i ++) {
				AttatchVO attatch = attDAO.selectAttatch(delAttrNos[i], sqlSession);
				attatchList.add(attatch);
				//attatch.deleteToBinary(saveFolder);
			}
			rowCnt = attDAO.deleteAttatchs(board, sqlSession);
			
			for(AttatchVO attatch : attatchList) {
				attatch.deleteToBinary(saveFolder);
			}
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
				int uploadResult = proccessboardImage(board, sqlSession);
				
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
		String encryptPass = utils.encryptSha512Base64(inputPass);
		if(!encryptPass.equals(saveBoard.getBoPass())){
			result = ServiceResult.INVALIDPASSWORD;
			return result;
		}
		
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			int rowcnt = boardDAO.updateBoard(board, sqlSession);
			System.out.println(rowcnt);
			if(rowcnt > 0) {
				rowcnt += proccessboardImage(board,sqlSession);
				deleteBoardFile(board,sqlSession);
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
		
		FreeBoardVO saveBoard = boardDAO.selectBoard(board.getBoNo());
		if(saveBoard ==null) {
			throw new DataNotFoundException(board.getBoNo().toString());
		}		
		ServiceResult result = null;
		if(EncryptUtils.matches(board.getBoPass(), saveBoard.getBoPass())) {
			try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				// 이진데이터의 저장명
				List<AttatchVO> attatchList = saveBoard.getAttatchList();
				String[] saveNames = null;
				if(!attatchList.isEmpty()) {
					saveNames = new String[attatchList.size()];
					for(int i = 0; i < saveNames.length; i++) {
						saveNames[i] = attatchList.get(i).getAttSavename();
					}
				}
				// 메타데이터 삭제
				int rowcnt = attDAO.deleteAll(board.getBoNo(), sqlSession);
				// 게시글 삭제
				rowcnt += boardDAO.deleteBoard(board.getBoNo(), sqlSession);
				if(rowcnt > 0) {
					// 이진데이터 삭제
					for(int i = 0; i < saveNames.length; i++) {
						File file = new File(saveFolder, saveNames[i]);
						file.delete();
					}
					result = ServiceResult.OK;
					sqlSession.commit();
				}// if end
			}// try end
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		
		return result;
		/*
		FreeBoardVO saveBoard = boardDAO.selectBoard(board.getBoNo());
		if(saveBoard ==null) {
			throw new DataNotFoundException();
		}		
		String inputPass = board.getBoPass();
		String encryptPass = utils.encryptSha512Base64(inputPass);
		String pass = saveBoard.getBoPass();
		System.out.println(pass);
		System.out.println(encryptPass);
		
		if(!pass.equals(encryptPass)) {
			return ServiceResult.INVALIDPASSWORD;
		}
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			int boNo = board.getBoNo();
			
			int rowCnt = attDAO.deleteAll(boNo, sqlSession);
				rowCnt += boardDAO.deleteBoard(boNo, sqlSession);
				rowCnt += deleteBoardAllFiles(saveBoard,sqlSession);
				sqlSession.commit();
				if(rowCnt >0) {
					return ServiceResult.OK;
				}else {
					return ServiceResult.FAIL;
				}
			
		}
		*/
	}

	

	@Override
	public AttatchVO download(int attNo) {
		
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			AttatchVO vo = attDAO.selectAttatch(attNo, sqlSession);
			if(vo == null) {
				throw new DataNotFoundException();
			}
			//attDAO.incrementDownCount(attNo);
			return vo;
		}
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
