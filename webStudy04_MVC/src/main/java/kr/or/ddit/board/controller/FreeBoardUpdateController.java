package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.board.service.FreeBoardServiceImpl;
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.resolvers.ModelAttribute;
import kr.or.ddit.mvc.resolvers.RequestParam;
import kr.or.ddit.mvc.resolvers.RequestPart;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;

@Controller
public class FreeBoardUpdateController {
	
	private static final Logger logger = LoggerFactory.getLogger(FreeBoardUpdateController.class);
	private FreeBoardService service = FreeBoardServiceImpl.getInstance();
	
	@RequestMapping("/board/boardUpdate.do")
	public String form(
			@RequestParam("boNo") int boNo,
			HttpServletRequest req
			) {
		FreeBoardVO board = service.retriveBoard(boNo);
		req.setAttribute("freeboard", board);
		
		List<AttatchVO> attatchVO = board.getAttatchList();
		for(AttatchVO att : attatchVO) {
			logger.info("{}",att);
		}
		
		return "board/boardForm";
	}
	
	@RequestMapping(value = "/board/boardUpdate.do", method = RequestMethod.POST)
	public String boardUpdate(
				@ModelAttribute("freeboard") FreeBoardVO board,
				@RequestPart(value = "boFiles", required = false) MultipartFile[] boardFile,
				HttpServletRequest req
			) {
		
		System.out.println(boardFile);
		String viewName = null;
		if(boardFile != null) {
			board.setBoFiles(boardFile);
		}
		Map<String, List<String>> errors = new HashMap<String, List<String>>();
		
		boolean valid = new ValidatorUtils<>().validate(board, errors, UpdateGroup.class);
		String message = null;
		
		if(valid) {
			try {
				ServiceResult result = service.modifyBoard(board);
				
				switch (result) {
				case OK:
					viewName = "redirect:/board/boardSelect.do?boNo=" + board.getBoNo();
					break;

				case INVALIDPASSWORD:
					viewName = "board/boardForm";
					message = "패스워드 오류";
					req.setAttribute("message", message);
					break;
				default:
					viewName = "board/boardForm";
					message = "잠시 후 다시 시도..";
					req.setAttribute("message", message);
					break;
				}
				
			}catch (DataNotFoundException e) {
				throw new RuntimeException(e);
			}
		
		}else{
			viewName = "board/boardForm";
		}
		
		
		return viewName;
		
		
	}
}















