package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.ZipVO;

@WebServlet("/member/memberZip.do")
public class MemberZipControllerServlet extends HttpServlet {

	private MemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<ZipVO> data = service.retrieveZipList();
		
		ObjectMapper mapper = new ObjectMapper();
		try(
			PrintWriter out = resp.getWriter();
		){
			mapper.writeValue(out, data);
		}
		
		
	}
}
