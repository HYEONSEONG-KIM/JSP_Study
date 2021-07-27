package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

@Controller
public class MyPageController{
	@Inject
	private MemberService service;
	
	@PreAuthorize("isFullyAuthenticated()")
	@RequestMapping("/mypage.do")
	public String mypage(
		@AuthenticationPrincipal(expression ="adaptee") MemberVO authMember,
//			Authentication authentication,
			Model model){
	//	MemberVO authMember = ((MemberVOWrapper)authentication.getPrincipal()).getAdaptee();
		MemberVO savedMember = service.retrieveMember(authMember.getMemId());
		
		model.addAttribute("member", savedMember);
		return "member/memberView";
	}
}













