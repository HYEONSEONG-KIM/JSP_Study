package kr.or.ddit.commons;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.ha.backend.Sender;

@WebServlet("/login/loginCheck.do")
public class LoginCheckServlet extends HttpServlet {

	private boolean authenicated(String id, String pass) {
		return id.equals(pass);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 파라미터 확보
		String id = req.getParameter("mem_id");
		String pass = req.getParameter("mem_pass");
		
		//2. 검증
		// 필수 파라미터 누락 여부 확인(400)
		if((id == null && id.isEmpty()) || (pass == null && pass.isEmpty())) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		boolean flag = authenicated(id, pass);
		
		//3. 인증
		//	1) 성공 : welcome page로 이동
		//		- redirect
		//	2) 실패 : login form page로 이동
		//		- dispatcher(forward)
		if(flag) {
			HttpSession session = req.getSession();
			session.setAttribute("authId", id);
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
		}else {
			req.setAttribute("id", id);
			RequestDispatcher rd = 
					req.getRequestDispatcher("/login/loginForm.jsp");
			rd.forward(req, resp);
			
		}
		
		
	}
}











