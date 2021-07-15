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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.resolvers.ModelAttribute;
import kr.or.ddit.mvc.resolvers.RequestParam;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.pagingVO;

@Controller
public class ProdRetrieveContorller{

	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	private static final Logger logger = LoggerFactory.getLogger(ProdRetrieveContorller.class);
	
	@RequestMapping("/prod/prodList.do")
	public String prodList(
			@RequestParam(value="page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("detailSearch") ProdVO prod,
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		// 검색 조건 : 상품명, 상품분류코드, 거래처코드(상세검색)
		// 동일 페이지내에서 정렬 조건 : 상품 분류별 정렬 및 최근 등록된 상품부터 조회
		String accept = req.getHeader("accept");
		
		pagingVO<ProdVO> paging = new pagingVO<>(5,3);
		
		paging.setDetailSearch(prod);
		paging.setCurrentPage(currentPage);
		
		service.retrieveProdList(paging);
		
		List<Map<String, Object>> prodLguList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList = othersDAO.selectBuyerList();
		
		
		//----------------------------------------
		req.setAttribute("paging", paging);
		req.setAttribute("prodLguList", prodLguList);
		req.setAttribute("buyerList", buyerList);
		
		logger.info("paging : {}", paging.getDataList());
		logger.info("prod : {}", prod);
		
		if(accept.contains("json")) {
			resp.setContentType("application/json;charset=utf-8");
			ObjectMapper mapper = new ObjectMapper();
			
			try(
				PrintWriter out = resp.getWriter();
			){
				mapper.writeValue(out, paging);
			}
			return null;
			
		}else {
		
			req.setCharacterEncoding("UTF-8");
			return "prod/prodList";
		}
	}
	
	@RequestMapping("/prod/prodView.do")
	public String prodView(@RequestParam("what") String prodId, HttpServletRequest req){
		
		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
		
		return "prod/prodView";
	
	}
}
