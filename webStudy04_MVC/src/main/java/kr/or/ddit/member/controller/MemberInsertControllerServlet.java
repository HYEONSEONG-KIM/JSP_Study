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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Insert;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.multipart.StandardMultipartHttpServletRequest;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberInsert.do")
@MultipartConfig
public class MemberInsertControllerServlet extends HttpServlet{
	
	private MemberService service = MemberServiceImpl.getInstance();

	private void addAttribute(HttpServletRequest req) {
		req.setAttribute("command", "INSERT");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req);
		String dest = "/WEB-INF/views/member/memberForm.jsp";
		req.getRequestDispatcher(dest).forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MemberVO member = new MemberVO();
		
		if(req instanceof StandardMultipartHttpServletRequest) {
			
			StandardMultipartHttpServletRequest wrapper = (StandardMultipartHttpServletRequest) req;
			
			MultipartFile file = wrapper.getFile("memImage");
			if(file != null && !file.isEmpty()) {
				member.setMemImage(file);
			}
		}
		
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("member", member);
		System.out.println(member);
		
		
		Map<String, List<String>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		ValidatorUtils<MemberVO> utils = new ValidatorUtils<>();
		
		boolean valid = utils.validate(member, errors, InsertGroup.class);
		System.out.println(errors);
		
		ServiceResult result = service.creatMember(member);
		String viewName = null;
		String msg = null;

		if(valid) {
		
			switch (result) {
			case OK:
				viewName = "redirect:/index.do";
				break;
			case PKDUPLICATED:
				viewName = "member/memberForm";
				msg = "???????????? ???????????? ?????? ???????????????";
				break;
			default:
				viewName = "member/memberForm";
				msg = "???????????? ??????";
				break;
			}
		}else {
			// memberForm.jsp??? ??????, ?????? errors??????, forward
			viewName = "member/memberForm"; 
		}
		
		
		
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			req.setAttribute("message", msg);
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
				
	}
	
	
	/*private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		
		if (StringUtils.isBlank(member.getMemId())) {
			valid = false;
			errors.put("memId", "??????ID ??????");
		}
		if (StringUtils.isBlank(member.getMemPass())) {
			valid = false;
			errors.put("memPass", "??????PASS ??????");
		}
		if (StringUtils.isBlank(member.getMemName())) {
			valid = false;
			System.out.println("check");
			errors.put("memName", "?????? ??????");
		}
		if (StringUtils.isBlank(member.getMemZip())) {
			valid = false;
			errors.put("memZip", "???????????? ??????");
		}
		if (StringUtils.isBlank(member.getMemAdd1())) {
			valid = false;
			errors.put("memAdd1", "?????? ??????");
		}
		if (StringUtils.isBlank(member.getMemAdd2())) {
			valid = false;
			errors.put("memAdd2", "???????????? ??????");
		}
		if (StringUtils.isBlank(member.getMemMail())) {
			valid = false;
			errors.put("memMail", "????????? ??????");
		}
		if(StringUtils.isNoneBlank(member.getMemMemorialday())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				sdf.parse(member.getMemMemorialday());
			} catch (ParseException e) {
				valid = false;
				errors.put("Memorialday", "?????? ?????? ??????");
			}
		}

		return valid;
	}
	*/
}














