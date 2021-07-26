package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MyPageController{
	@Inject
	private MemberService service;
	
	@RequestMapping("/mypage.do")
	public String mypage(
			@SessionAttribute(name = "authMember", required = true) MemberVO authMember,
			Model model){
		
		MemberVO savedMember = service.retrieveMember(authMember.getMemId());
		
		model.addAttribute("member", savedMember);
		return "member/memberView";
	}
}













