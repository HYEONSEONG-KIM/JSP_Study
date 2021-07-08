package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodView.do")
public class ProdSelectControllerServlet extends HttpServlet {
	
	private ProdService service = new ProdServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String prodId = req.getParameter("what");
		

		
		if(StringUtils.isBlank(prodId)) {
			resp.sendError(400,"잘못된 파라미터");
			return;
		}
		
		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
		
		
		String viewName = "prod/prodView";
		
		if(viewName.startsWith("redirect:")) {
			viewName  = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
			
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
		
	
	}
}
