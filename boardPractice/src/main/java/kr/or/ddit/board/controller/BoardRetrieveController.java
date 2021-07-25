package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;

@Controller
public class BoardRetrieveController {

	@Inject
	private BoardService service;
	
	@RequestMapping("/board/boardList.do")
	public String boardList(
			@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage
			, Model model
			) {
		
		String viewName = "board/boardList";
		
		PagingVO<BoardVO> paging = new PagingVO<>(10,10);
		
		paging.setCurrentPage(currentPage);
		service.retreiveBoardList(paging);
		
		model.addAttribute("paging", paging);
		
		
		return viewName;
		
	}
	
}
