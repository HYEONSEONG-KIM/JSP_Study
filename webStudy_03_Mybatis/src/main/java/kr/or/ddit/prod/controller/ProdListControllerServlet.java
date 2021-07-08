package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.pagingVO;

@WebServlet("/prod/prodList.do")
public class ProdListControllerServlet extends HttpServlet{

	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 검색 조건 : 상품명, 상품분류코드, 거래처코드(상세검색)
		// 동일 페이지내에서 정렬 조건 : 상품 분류별 정렬 및 최근 등록된 상품부터 조회
		req.setCharacterEncoding("UTF-8");
		
		String pageParam = req.getParameter("page");
		String prodLgu = req.getParameter("prodLgu");
		String prodBuyer = req.getParameter("prodBuyer");
		String prodName = req.getParameter("prodName");
		String accept = req.getHeader("accept");
		
		int currentPage = 1;
		
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		
		pagingVO<ProdVO> paging = new pagingVO<>(5,3);
		ProdVO prod = new ProdVO();
		
		prod.setProdLgu(prodLgu);
		prod.setProdBuyer(prodBuyer);
		prod.setProdName(prodName);
		
		paging.setDetailSearch(prod);
		paging.setCurrentPage(currentPage);
		
		service.retrieveProdList(paging);
		
		List<Map<String, Object>> prodLguList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList = othersDAO.selectBuyerList();
		
		
		//----------------------------------------
		req.setAttribute("paging", paging);
		req.setAttribute("prodLguList", prodLguList);
		req.setAttribute("buyerList", buyerList);
		
		if(accept.contains("json")) {
			resp.setContentType("application/json;charset=utf-8");
			ObjectMapper mapper = new ObjectMapper();
			
			try(
				PrintWriter out = resp.getWriter();
			){
				mapper.writeValue(out, paging);
			}
			
		}else {
		
			req.setCharacterEncoding("UTF-8");
			String viewName = "prod/prodList";
			
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
