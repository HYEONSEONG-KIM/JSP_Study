package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.board.service.FreeBoardServiceImpl;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.FreeBoardVO;

@Controller
@RequestMapping("/board/boardInsert.do")
public class FreeBoardInsertController {
	
	@Inject
	private FreeBoardService service;

	@GetMapping
	public String freeBoardForm(
			@ModelAttribute("freeboard")FreeBoardVO board
			) {
		String viewName = "board/boardForm";
		return viewName;
	}
	
	@PostMapping
	public String insertBaord(
			@Validated(InsertGroup.class) @ModelAttribute("freeboard") FreeBoardVO freeBoardVO,
			BindingResult errors,
			Model model
			) {
		
		String viewName = null;
	
		ServiceResult result = service.createBoard(freeBoardVO); 
		
		String message = null;
		
		if(errors.hasErrors()) {
			System.out.println("valid 실패");
			viewName = "board/boardForm";
		}else {
			
			if(result == ServiceResult.OK) {
				viewName = "redirect:/board/boardList.do";
			}else {
				viewName = "board/boardForm";
				message = "잠시 후 시도";
			}
		}
		model.addAttribute("message", message);
		return viewName;
		
	}
}









