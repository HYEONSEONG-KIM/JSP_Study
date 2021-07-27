package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

/**
 * 로그인되어있는 사용자에 대한 탈퇴 처리.
 *
 */
@Controller
public class MemberDeleteController{
	
	@Inject
	private MemberService service;
	
	@RequestMapping(value="/member/memberDelete.do", method=RequestMethod.POST)
	public String memberDelete(
			@AuthenticationPrincipal(expression = "adaptee") MemberVO authMember,
			@RequestParam("memPass") String memPass,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication){
		
		MemberVO member = MemberVO.builder()
								.memId(authMember.getMemId())
								.memPass(memPass)
								.build();
		String message = null;
		String viewName = null;
		ServiceResult result = service.removeMember(member);
		switch (result) {
		case OK:
//			welcome page : redirect
			SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
			logoutHandler.logout(request, response, authentication);
			viewName = "redirect:/";
			break;
		case INVALIDPASSWORD:
//			mypage, redirect
			viewName = "redirect:/mypage.do";
			message = "비밀번호 오류";
			break;

		default:
//			mypage,redirect
			viewName = "redirect:/mypage.do";
			message = "서버 오류, 쫌따 다시.";
			break;
		}
		
		if(message!=null)
			redirectAttributes.addFlashAttribute("message", message);
		
		return viewName;
	}
}












