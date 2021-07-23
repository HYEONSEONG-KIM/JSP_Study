package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.FreeBoardDAO;
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.utils.EncryptUtils;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.pagingVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FreeBoardServiceImpl implements FreeBoardService {

	private static final Logger logger = LoggerFactory.getLogger(FreeBoardServiceImpl.class);

	@Inject
	private FreeBoardDAO boardDAO;
	@Inject
	private AttatchDAO attDAO;

	@Value("#{appInfo.attatchPath}")
	private File saveFolder;
	
	@PostConstruct
	public void init() {
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		log.info("첨부파일 저장 경로 : {}", saveFolder.getAbsolutePath());
	}
	
	private int proccessboardImage(FreeBoardVO board) {
		
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList == null || attatchList.isEmpty()) return 0;
		
		int rowcnt = attDAO.insertAttatchs(board);
		
		try {
			for(AttatchVO attatch : attatchList) {
				attatch.saveToBinary(saveFolder);
			}
			return rowcnt;
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	EncryptUtils utils = new EncryptUtils();
	private int deleteBoardAllFiles(FreeBoardVO saveBoard) {
		
		List<AttatchVO> attatchList = saveBoard.getAttatchList();
		if(attatchList == null || attatchList.isEmpty()) return 0;
		
		int cnt = 0;
		
		for(AttatchVO attatch : attatchList) {
			attatch.deleteToBinary(saveFolder);
			cnt++;
		}
		
		return cnt;
	}

	
	
	private int deleteBoardFile(FreeBoardVO board) {
		
		int[] delAttrNos = board.getDelAttrNos();
		if(delAttrNos == null) return 0;
		int rowCnt = 0;
		List<AttatchVO> attatchList = new ArrayList<>();
		try {
			for(int i = 0; i < delAttrNos.length; i ++) {
				AttatchVO attatch = attDAO.selectAttatch(delAttrNos[i]);
				attatchList.add(attatch);
				//attatch.deleteToBinary(saveFolder);
			}
			rowCnt = attDAO.deleteAttatchs(board);
			
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
		
	
			int boardResult = boardDAO.insertBoard(board);
			if(boardResult > 0) {
				proccessboardImage(board);
					result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
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
		
		
			int rowcnt = boardDAO.updateBoard(board);
			System.out.println(rowcnt);
			if(rowcnt > 0) {
				rowcnt += proccessboardImage(board);
				deleteBoardFile(board);
				
				result = ServiceResult.OK;
				
			}else {
				result = ServiceResult.FAIL;
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
				int rowcnt = attDAO.deleteAll(board.getBoNo());
				// 게시글 삭제
				rowcnt += boardDAO.deleteBoard(board.getBoNo());
				if(rowcnt > 0) {
					// 이진데이터 삭제
					for(int i = 0; i < saveNames.length; i++) {
						File file = new File(saveFolder, saveNames[i]);
						file.delete();
					}
					result = ServiceResult.OK;
				}// if end
			
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
		
		
			AttatchVO vo = attDAO.selectAttatch(attNo);
			if(vo == null) {
				throw new DataNotFoundException();
			}
			//attDAO.incrementDownCount(attNo);
			return vo;
		
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
