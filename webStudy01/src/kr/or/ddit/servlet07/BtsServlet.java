package kr.or.ddit.servlet07;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumtype.BtsType;

@WebServlet("/bts")
public class BtsServlet extends HttpServlet {

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/08/btsForm.jsp");
			rd.forward(req, resp);
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String memName = req.getParameter("btsMember");
			if(memName == null && memName.isEmpty()) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			String name = BtsType.findMember(memName);
			
			String path = "/WEB-INF/views/bts/"+ name + ".jsp";
			System.out.println(path);
			RequestDispatcher rd = req.getRequestDispatcher(path);
			rd.forward(req, resp);
		}
		
}
