package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

/**
 * 로그인된 사용자가 자기 정보를 수정함.
 *
 */
@Controller
@RequestMapping("/member/memberUpdate.do")
public class MemberUpdateController{
	@Inject
	private MemberService service;
	@Inject
	private AuthenticateService authService;

	@ModelAttribute("command")
	public String addAttribute() {
		return "UPDATE";		
	}
	
	@GetMapping
	public String updateForm(
			@SessionAttribute(name = "authMember", required = true) MemberVO authMember,
			Model model){
		MemberVO member = service.retrieveMember(authMember.getMemId());
		model.addAttribute("member", member);

		return "member/memberForm";
	}

	@PostMapping
	public String updateProcess(
			@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member,
			Errors errors,
			HttpSession session,
			Model model) throws IOException{
		
		String viewName = null;
		String message = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case OK:
//			mypage 로 이동 , redirect
				MemberVO changedMember = (MemberVO) authService.authenticate(member);
				session.setAttribute("authMember", changedMember);
				viewName = "redirect:/mypage.do";
				break;
			case INVALIDPASSWORD:
//				memberForm.jsp 로 이동, forward
				viewName = "member/memberForm";
				message = "비밀번호 오류";
				break;
			default:
//				memberForm.jsp 로 이동, forward
				viewName = "member/memberForm";
				message = "서버 오류, 쫌따 다시 하셈.";
				break;
			}
		} else {
//			memberForm.jsp로 이동, forward
			viewName = "member/memberForm";
		}
		model.addAttribute("message", message);

		return viewName;
	}
}








