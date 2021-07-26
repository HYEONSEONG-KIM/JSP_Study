package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.commons.UserNotFoundExcpetion;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

@Controller
public class IdCheckController{
	
	@Inject
	private MemberService service;
	
	@RequestMapping(value = "/member/idCheck.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public boolean idCheck(@RequestParam("memId") String memId) {
		boolean validId = false;
		try {
			service.retrieveMember(memId);
			validId = false;
		}catch (UserNotFoundExcpetion e) {
			validId = true;
		}
		
		return validId;
	}
}








