package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberList.do")
public class MemberListControllerServlet extends HttpServlet{

	// member service 와의 의존관계
	MemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		memberList 라는 이름의 속성으로 회원목록 공유
		List<MemberVO> memberList = service.retrieveMemberList();
		
		req.setAttribute("memberList", memberList);
		
		String dest = "/WEB-INF/views/member/memberList.jsp";
		req.getRequestDispatcher(dest).forward(req, resp);
		
	}
}
