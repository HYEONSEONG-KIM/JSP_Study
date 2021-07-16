package kr.or.ddit.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.dao.FreeBoardDAO;
import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.board.service.FreeBoardServiceImpl;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.resolvers.ModelAttribute;
import kr.or.ddit.mvc.resolvers.RequestParam;
import kr.or.ddit.vo.ExtendSearchVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.pagingVO;

@Controller
public class FreeBoardRetrieveController {
	
	private FreeBoardService service = FreeBoardServiceImpl.getInstance();
	private static final Logger logger = LoggerFactory.getLogger(FreeBoardRetrieveController.class);
	
	@RequestMapping("/board/boardList.do")
	public String list(
			@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage
			,@ModelAttribute("simpleSearch") ExtendSearchVO simpleSearch
			,HttpServletRequest req) {
		// 동기방식으로 조회
		// 검색 조건(제목 ,작성자명 ,내용)
		// 정렬 조건(최신에 작성한 글 먼저)
		pagingVO<FreeBoardVO> paging = new pagingVO<>(10,10);
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		
		int totalRecord = service.retriveBoardCount(paging);
		paging.setTotalRecord(totalRecord);
		
		List<FreeBoardVO> boardList = service.retriveBoardList(paging);
		paging.setDataList(boardList);
		
		req.setAttribute("paging", paging);
		
		return "board/boardList";
	}



// 첨부파일 테이블정보 조인
	@RequestMapping("/board/boardSelect.do")
	public String select(
			@RequestParam("boNo") int boNo,
			HttpServletRequest req
		) {
		
		String viewName = "board/boardSelect";
		
		FreeBoardVO board = service.retriveBoard(boNo);
		req.setAttribute("freeboard", board);
		
		return viewName;
		
	}

}











