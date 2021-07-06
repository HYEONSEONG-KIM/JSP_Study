package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.pagingVO;

@WebServlet("/member/memberList.do")
public class MemberListControllerServlet extends HttpServlet{

	// member service 와의 의존관계
	MemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// current Page : page parameter
		String pageParam = req.getParameter("page");
		String searchType = req.getParameter("searchType");
		String searchWord = req.getParameter("searchWord");
		
		SearchVO simpleSearch = new SearchVO(searchType, searchWord);
		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		pagingVO<MemberVO> pagingVO = new pagingVO<>(5,2);
		pagingVO.setSimpleSearch(simpleSearch);
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
//		memberList 라는 이름의 속성으로 회원목록 공유
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO);
		
		pagingVO.setDataList(memberList);
		
		req.setAttribute("paging", pagingVO);
		
		String dest = "/WEB-INF/views/member/memberList.jsp";
		req.getRequestDispatcher(dest).forward(req, resp);
		
	}
}
