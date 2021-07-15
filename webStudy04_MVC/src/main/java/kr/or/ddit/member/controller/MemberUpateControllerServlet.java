package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.multipart.StandardMultipartHttpServletRequest;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import oracle.jdbc.driver.Message;

/**
 * 	로그인된 사용자가 자기 정보를 수정
 *	
 *
 */
@WebServlet("/member/memberUpdate.do")
@MultipartConfig
public class MemberUpateControllerServlet extends HttpServlet{
	
	private MemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String memId = authMember.getMemId();
		
		MemberVO member = service.retrieveMember(memId);
		
		req.setAttribute("member",member);
		String dest = "/WEB-INF/views/member/memberForm.jsp";
		req.getRequestDispatcher(dest).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// parsing : 문자열 -> 특정 타입(TO_DATE, TO_NUMBER)
		// formatting : 특정타입 -> 문자열(TO_CHAR)
		
		
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		
		if(req instanceof StandardMultipartHttpServletRequest) {
			MultipartFile memImage = ((StandardMultipartHttpServletRequest) req).getFile("memImage");
			member.setMemImage(memImage);
		}

		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		
		
		Map<String, List<String>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		ValidatorUtils<MemberVO> validateUtils = new ValidatorUtils<>();
		
		boolean valid = validateUtils.validate(member, errors, UpdateGroup.class);
		String viewName = null;
		String message = null;
		if(valid) {
			
			ServiceResult result = service.modifyMember(member);
			
			switch (result) {
			case OK:
				// 마이페이지 이동, redirect
				viewName = "redirect:/mypage.do";
				break;

			case INVALIDPASSWORD :
				// memberForm.jsp로 이동,msg 멤버 이동, forward
				message = "비밀번호 오류";
				viewName = "member/memberForm";
				
				break;
				
			default:
				// memberForm.jsp 이동, msg 멤버 이동, forward
				message = "서버오류, 잠시 후 다시 실행";
				viewName = "member/memberForm"; 
				break;
			}
			
		}else {
			// memberForm.jsp로 이동, 멤버 errors이동, forward
			viewName = "member/memberForm"; 
		}
		
		req.setAttribute("message", message);
		
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
	
	}

	/**
	 * Member 테이블의 제약조건에 따른 검증
	 * @param member
	 * @param errors
	 * @return
	 *//*
	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		
		if (StringUtils.isBlank(member.getMemId())) {
			valid = false;
			errors.put("memId", "회원ID 누락");
		}
		if (StringUtils.isBlank(member.getMemPass())) {
			valid = false;
			errors.put("memPass", "회원PASS 누락");
		}
		if (StringUtils.isBlank(member.getMemName())) {
			valid = false;
			System.out.println("check");
			errors.put("memName", "이름 누락");
		}
		if (StringUtils.isBlank(member.getMemZip())) {
			valid = false;
			errors.put("memZip", "우편번호 누락");
		}
		if (StringUtils.isBlank(member.getMemAdd1())) {
			valid = false;
			errors.put("memAdd1", "주소 누락");
		}
		if (StringUtils.isBlank(member.getMemAdd2())) {
			valid = false;
			errors.put("memAdd2", "상세주소 누락");
		}
		if (StringUtils.isBlank(member.getMemMail())) {
			valid = false;
			errors.put("memMail", "이메일 누락");
		}
		if(StringUtils.isNoneBlank(member.getMemMemorialday())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				sdf.parse(member.getMemMemorialday());
			} catch (ParseException e) {
				valid = false;
				errors.put("Memorialday", "날짜 형식 확인");
			}
		}

		return valid;
	}*/
}











