package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.commons.UserNotFoundExcpetion;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.pagingVO;

@Controller
@RequestMapping("/member/")
public class MemberRetrieveController{
	// member service 와의 의존관계
	@Inject
	private MemberService service;
	
	@RequestMapping("memberList.do")
	public String list(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@ModelAttribute("simpleSearch") SearchVO simpleSearch,
			Model model
			)  {
		pagingVO<MemberVO> pagingVO  = new pagingVO<>(5, 2);
		pagingVO.setSimpleSearch(simpleSearch);
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
//		memberList 라는 이름의 속성으로 회원 목록 공유.
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		model.addAttribute("pagingVO", pagingVO);
		
		return "member/memberList";
		
	}
	
	
	@RequestMapping("memberView.do")
	public String view(@RequestParam("who") String memId, 
			Model model) {

			MemberVO member = service.retrieveMember(memId);
			model.addAttribute("member", member);
			return "/member/memberView";
			

	}
}












