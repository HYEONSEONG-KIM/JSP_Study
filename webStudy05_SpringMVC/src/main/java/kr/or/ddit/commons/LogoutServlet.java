package kr.or.ddit.commons;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutServlet {

	@RequestMapping("/login/logout.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		// 최초 요청이라면(로그아웃 이므로 최초 요청은 아니지만 세션이 만료되었을 수도 있는 상황)
		if(session == null || session.isNew()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "로그아웃이 최초의 요청??");
			return null;
		}
//		session.removeAttribute("authId");
		session.invalidate();
		String message = URLEncoder.encode("로그아웃 성공", "UTF-8");
		
		return "redirect:/?message=" + message;
	}
}
