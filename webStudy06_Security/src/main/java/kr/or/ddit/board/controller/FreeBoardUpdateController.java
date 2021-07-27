package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.board.service.FreeBoardServiceImpl;
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;

@Controller
@RequestMapping("/board/boardUpdate.do")
public class FreeBoardUpdateController {
	
	private static final Logger logger = LoggerFactory.getLogger(FreeBoardUpdateController.class);
	@Inject
	private FreeBoardService service;
	
	@GetMapping
	public String form(
			@RequestParam("boNo") int boNo,
			Model model
			) {
		FreeBoardVO board = service.retriveBoard(boNo);
		model.addAttribute("freeboard", board);
		
		List<AttatchVO> attatchVO = board.getAttatchList();
		for(AttatchVO att : attatchVO) {
			logger.info("{}",att);
		}
		
		return "board/boardForm";
	}
	
	
	@PostMapping
	public String boardUpdate(
				@Validated(UpdateGroup.class) @ModelAttribute("freeboard") FreeBoardVO board,
				Errors errors,// 통과를 못했다면 errors를 통해 이유를 알려줌
				Model model
			) {

		String viewName = null;
		
//		Map<String, List<String>> errors = new HashMap<String, List<String>>();
//		
//		boolean valid = new ValidatorUtils<>().validate(board, errors, UpdateGroup.class);
		String message = null;
		
		if(!errors.hasErrors()) {
			try {
				ServiceResult result = service.modifyBoard(board);
				
				switch (result) {
				case OK:
					viewName = "redirect:/board/boardSelect.do?boNo=" + board.getBoNo();
					break;

				case INVALIDPASSWORD:
					viewName = "board/boardForm";
					message = "패스워드 오류";
					model.addAttribute("message", message);
					break;
				default:
					viewName = "board/boardForm";
					message = "잠시 후 다시 시도..";
					model.addAttribute("message", message);
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















