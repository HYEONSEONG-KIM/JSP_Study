package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.commons.UserNotFoundExcpetion;
import kr.or.ddit.enumtype.MimeType;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberView.do")
public class MemberViewControllerServlet extends HttpServlet{

	private MemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String dest = "/WEB-INF/views/member/memberView.jsp";
		
		String accept = req.getHeader("accept");
		
		String contentType = MimeType.findMimetext(accept);
		resp.setContentType(contentType);
		
		// 파라미터 받기
		String memId = req.getParameter("who");
		// 검증(에러시 400번대 에러)
		int status = 200;
		String msg = null;
		
		MemberVO member = null;
		
		if(StringUtils.isBlank(memId)) {
			status = HttpServletResponse.SC_BAD_REQUEST;
			msg = "잘못된 파라미터 입니다";
		}else {
			// DB에서 회원 가져오기 (에러시 에러처리)
			
			try {
				// spoke
				member = service.retrieveMember(memId);
				req.setAttribute("member", member);
			}catch(UserNotFoundExcpetion e) {
				status = 404;
				msg = e.getMessage();
			}
			
		}
		
		
		if(status == 200) {
			
			req.getRequestDispatcher(dest).forward(req, resp);

			
		}else {
			resp.sendError(status,msg);
		}
	}
}
