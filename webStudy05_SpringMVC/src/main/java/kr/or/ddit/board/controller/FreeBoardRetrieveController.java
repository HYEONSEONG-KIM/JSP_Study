package kr.or.ddit.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.board.service.FreeBoardServiceImpl;
import kr.or.ddit.vo.ExtendSearchVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.pagingVO;

@Controller
public class FreeBoardRetrieveController {
	
	private FreeBoardService service;
	@Inject
	public void setService(FreeBoardService service) {
		this.service = service;
		System.err.printf("%s, proxy 여부 : %b\n",service.getClass().getName(), AopUtils.isAopProxy(service));
	}
	private static final Logger logger = LoggerFactory.getLogger(FreeBoardRetrieveController.class);
	
	
	@RequestMapping(value = "/board/boardList.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public pagingVO<FreeBoardVO> listForAjax(
			@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage
			,@ModelAttribute("simpleSearch") ExtendSearchVO simpleSearch
			
	) {
		pagingVO<FreeBoardVO> paging = new pagingVO<>(10,10);
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		
		int totalRecord = service.retriveBoardCount(paging);
		paging.setTotalRecord(totalRecord);
		
		List<FreeBoardVO> boardList = service.retriveBoardList(paging);
		paging.setDataList(boardList);
		
		
		return paging;
		
	}
	
	@RequestMapping("/board/boardList.do")
	public String list(
			@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage
			,@ModelAttribute("simpleSearch") ExtendSearchVO simpleSearch
			,Model model) {
	
		pagingVO<FreeBoardVO> paging = listForAjax(currentPage, simpleSearch);
		
		model.addAttribute("paging", paging);
		
		return "board/boardList";
	}



// 첨부파일 테이블정보 조인
	@RequestMapping("/board/boardSelect.do")
	public String select(
			@RequestParam("boNo") int boNo,
			Model model
		) {
		
		String viewName = "board/boardSelect";
		
		FreeBoardVO board = service.retriveBoard(boNo);
		model.addAttribute("freeboard", board);
		
		return viewName;
		
	}

}











