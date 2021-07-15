package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.buyer.service.buyerService;
import kr.or.ddit.buyer.service.buyerServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.pagingVO;

@WebServlet("/buyer/buyerList.do")
public class BuyerListControllerServlet extends HttpServlet {

	private buyerService service = buyerServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String searchWord = req.getParameter("searchWord");
		String pageStr = req.getParameter("page");
		int currentPage = 1;
		if(StringUtils.isNumeric(pageStr)) {
			currentPage = Integer.parseInt(pageStr);
		}
		
		
	
		
		String accept = req.getHeader("accept");
		String viewName = null;
		
		if(StringUtils.contains(accept, "json")) {
			resp.setContentType("application/json; charset=UTF-8");
			
			pagingVO<BuyerVO> paging = new pagingVO<>(3, 3);
			paging.setCurrentPage(currentPage);
			
			BuyerVO buyer = new BuyerVO();
			
			if(StringUtils.isNotBlank(searchWord)) {
				buyer.setBuyerName(searchWord);
				buyer.setBuyerCharger(searchWord);
				paging.setDetailSearch(buyer);
			}
			
			service.retrieveBuyerList(paging);
			
			ObjectMapper mapper = new ObjectMapper();
			
			try(
				PrintWriter out = resp.getWriter();
			){
				mapper.writeValue(out, paging);
			}
			
			
			
		}else {
			
			viewName = "buyer/buyerList";
			
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
	
}
