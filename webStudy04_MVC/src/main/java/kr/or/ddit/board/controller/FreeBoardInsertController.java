package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.board.service.FreeBoardServiceImpl;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.resolvers.ModelAttribute;
import kr.or.ddit.mvc.resolvers.RequestPart;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.FreeBoardVO;

@Controller
public class FreeBoardInsertController {
	
	private FreeBoardService service = FreeBoardServiceImpl.getInstance();

	@RequestMapping("/board/boardInsert.do")
	public String freeBoardForm(
			@ModelAttribute("freeboard")FreeBoardVO board
			) {
		String viewName = "board/boardForm";
		return viewName;
	}
	
	@RequestMapping(value = "/board/boardInsert.do", method = RequestMethod.POST)
	public String insertBaord(
			@ModelAttribute("freeboard") FreeBoardVO freeBoardVO,
			@RequestPart(value = "boFiles", required = false) MultipartFile[] boFiles,
			HttpServletRequest req
			) {
		
		String viewName = null;
		if(boFiles != null) {
			freeBoardVO.setBoFiles(boFiles);
		}
		ServiceResult result = service.createBoard(freeBoardVO); 
		
		Map<String, List<String>> errors = new HashMap<>();
		
		boolean valid = new ValidatorUtils<>().validate(freeBoardVO, errors, InsertGroup.class);
		req.setAttribute("errors", errors);
		
		if(!valid) {
			System.out.println("valid 실패");
			viewName = "board/boardForm";
		}else {
			
			if(result == ServiceResult.OK) {
				viewName = "redirect:/board/boardList.do";
			}else {
				System.out.println("service시류ㅐ");
				viewName = "board/boardForm";
			}
		}
		
		return viewName;
		
	}
}









