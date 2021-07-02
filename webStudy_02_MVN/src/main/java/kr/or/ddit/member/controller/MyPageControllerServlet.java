package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/mypage.do")
public class MyPageControllerServlet extends HttpServlet{

	private MemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		MemberVO sessionMember = (MemberVO) session.getAttribute("authMember");
		String dest = "/WEB-INF/views/member/memberView.jsp";
		
		int status = 200;
		String msg = null;
		
		if(sessionMember == null) {
			status = 400;
			msg = "잘못된 요청입니다";
		}else {
			try {
				String mem_id = sessionMember.getMem_id();
				MemberVO member = service.retrieveMember(mem_id);
				req.setAttribute("member", member);
			}catch (Exception e) {
				status = 404;
				msg = e.getMessage();
			}
		}
		
		if(status ==200) {
			req.getRequestDispatcher(dest).forward(req, resp);
		}else {
			resp.sendError(status,msg);
		}
	}
}
