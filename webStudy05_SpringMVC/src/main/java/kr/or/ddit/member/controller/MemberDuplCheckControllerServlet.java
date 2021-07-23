package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.commons.UserNotFoundExcpetion;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/IdCheck.do")
public class MemberDuplCheckControllerServlet extends HttpServlet {

	private MemberService service = MemberServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=utf-8");
		String memId = req.getParameter("memId");
		String result = null;
		
		try{
			MemberVO member = service.retrieveMember(memId);
			result = "이미존재하는 아이디 입니다";
		}catch (UserNotFoundExcpetion e) {
			result = "true";
		}
		
		
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(result);
		System.out.println(json);
		
		try(
			PrintWriter out = resp.getWriter();
		){
			out.print(json);
		}
	}
	
}
