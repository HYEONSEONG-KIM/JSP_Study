package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;


@Controller
@SessionAttributes(names = "member")
@RequestMapping("/member/memberInsert.do")
public class MemberInsertController{
	
	@Inject
	private MemberService service;
	
	@ModelAttribute("command")
	public String addAttribute() {
		return "INSERT";		
	}

	@ModelAttribute("member")
	public MemberVO member() {
		return new MemberVO();
	}
	
	@GetMapping
	public String insertForm(HttpServletRequest req){
		return "member/memberForm";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String memberInsert(
			@Validated(InsertGroup.class) @ModelAttribute("member") MemberVO member, 
			Errors errors,
			RedirectAttributes redirectAttributes,
			SessionStatus sessionStatus){
		
		

		String viewName = null;
		String message = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case OK:
//			mypage 로 이동 , redirect
				viewName = "redirect:/login/loginForm.jsp";
				sessionStatus.setComplete(); // 현재 session에 대한 커맨드가 완료 -> member라는 session 데이터가 사라짐
				break;
			case PKDUPLICATED:
//				memberForm.jsp 로 이동, forward
				viewName = "redirect:/member/memberInsert.do";
				message = "아이디 중복, 바꾸셈.";
				break;
			default:
//				memberForm.jsp 로 이동, forward
				viewName = "redirect:/member/memberInsert.do";
				message = "서버 오류, 쫌따 다시 하셈.";
				break;
			}
		} else {
//			memberForm.jsp로 이동, forward
			viewName ="redirect:/member/memberInsert.do";
			// errors를 session에 담기위한 key
			String attName =  BindingResult.class.getName() + ".member";

			redirectAttributes.addFlashAttribute(attName, errors);
		}

		redirectAttributes.addFlashAttribute("message", message);

		return viewName;
	}

}








