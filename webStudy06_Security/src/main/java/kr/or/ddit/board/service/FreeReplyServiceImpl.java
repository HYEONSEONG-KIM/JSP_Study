package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.board.dao.FreeReplyDAO;
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.FreeReplyVO;
import kr.or.ddit.vo.pagingVO;

@Service
public class FreeReplyServiceImpl implements FreeReplyService {

	@Inject
	private FreeReplyDAO replyDao;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public void retrieveReply(pagingVO<FreeReplyVO> paging, int boNo){
		
		int record = replyDao.totalReplyRecored(boNo);
		List<FreeReplyVO> list = replyDao.boardReplyList(paging);
		
		paging.setTotalRecord(record);
		paging.setDataList(list);
	}

	@Transactional
	@Override
	public ServiceResult createReply(FreeReplyVO reply) {
		
		String pass = reply.getRepPass();
		pass = passwordEncoder.encode(pass);
		reply.setRepPass(pass);
		
		int rowcnt = replyDao.insertBoardReply(reply);
		ServiceResult result = null;
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult modifyReply(FreeReplyVO reply) {
		
		return null;
	}

	@Transactional
	@Override
	public ServiceResult removeReply(FreeReplyVO reply) {
		
		ServiceResult result = null;
		String inputPass = reply.getRepPass();
		FreeReplyVO savedVO = replyDao.selectReply(reply.getRepNo());
		if(savedVO == null) {
			throw new DataNotFoundException();
		}
		String saved = savedVO.getRepPass();
		
		if(!passwordEncoder.matches(inputPass, saved)) {
			result = ServiceResult.INVALIDPASSWORD;
			return result;
		}
		
		
		int rowcnt = replyDao.deleteBoardReply(reply.getRepNo());
		if(rowcnt >0 ) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		
		
		return result;
	}

}
