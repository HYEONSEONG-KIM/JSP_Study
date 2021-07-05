package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

/**
 * 로그인 되어있는 사용자에 대한 탈퇴처리
 * 
 */
@WebServlet("/member/memberDelete.do")
public class MemberDeleteControllerServlet extends HttpServlet{

	private MemberService service = MemberServiceImpl.getInstance();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pass = req.getParameter("memPass");
		
		if(StringUtils.isBlank(pass)) {
			resp.sendError(400);
		}
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		member.setMemPass(pass);
		String message = null;
		String viewName = null;
		ServiceResult result =  service.removeMember(member);
		
		switch (result) {
		case OK:
			//welcom page -> redirect
			viewName = "redirect:/index.do";
			session.removeAttribute("authMember");
			break;

		case INVALIDPASSWORD:
			// mypage -> redirect
			message = "비밀번호 오류";
			viewName = "redirect:/mypage.do";
			break;
			
		default:
			// mypage -> redirect
			message = "서버 오류, 잠시 후 다시 시도";
			viewName = "redirect:/mypage.do";
			break;
		}
		
		session.setAttribute("deleteMessage", message);
		
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
		
		
	}
}

























