package kr.or.ddit.servlet07;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/innerAccess.do")
public class Model2TestSerlvet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contents = "김치찌개";
		req.setAttribute("contents", contents);
		HttpSession session = req.getSession();
		session.setAttribute("contents", contents);
		String dest = req.getContextPath() + "/07/destination.jsp";
//		String dest = "/WEB-INF/views/inner.jsp";
		resp.sendRedirect(dest);
//		RequestDispatcher rd = req.getRequestDispatcher(dest);
//		rd.forward(req, resp);
	}
}























