package kr.or.ddit.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.dao.FreeReplyDAO;
import kr.or.ddit.board.service.FreeReplyService;
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.FreeReplyVO;
import kr.or.ddit.vo.pagingVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/reply")
public class FreeReplyController {

	@Inject
	private FreeReplyService service;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public pagingVO<FreeReplyVO> replyList(
			int boNo,
			@RequestParam(required = false, defaultValue = "1") int page
			) {
		pagingVO<FreeReplyVO> paging = new pagingVO<>(3,3);
		paging.setBoNo(boNo);
		paging.setCurrentPage(page);
		service.retrieveReply(paging, boNo);
		
		return paging;
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ServiceResult insertReply(
			@Validated(InsertGroup.class) @ModelAttribute("reply") FreeReplyVO reply,
			Errors errors
	) {
		ServiceResult result = service.createReply(reply);
		return result;
	}
	
	
	@RequestMapping(value = "{repNo}/{repPass}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ServiceResult deleteReply(
				@PathVariable Integer repNo,
				@PathVariable String repPass
//			HttpServletRequest req
			) {
//		String repNo = req.getParameter("repNo");
//		String repPass = req.getParameter("repPass");
		log.info("{}, {}",repNo, repPass);
		FreeReplyVO reply = new FreeReplyVO();
		reply.setRepNo(repNo);
		reply.setRepPass(repPass);
		log.info("{}", reply);
		try {
			ServiceResult result = service.removeReply(reply);
			return result;
		}catch (DataNotFoundException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}













