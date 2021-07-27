package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.buyer.service.buyerService;
import kr.or.ddit.buyer.service.buyerServiceImpl;
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.pagingVO;

//@Controller
public class Buyer {

	private buyerService service = buyerServiceImpl.getInstance();
	
	@RequestMapping("/buyer/buyerList.do")
	public String buyerList(
			@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage
			,@RequestParam(value = "searchWord", required =false) String searchWord
			,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
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
			return null;
		}else {
			return "buyer/buyerList";
		}
	}
		@RequestMapping("/buyer/buyerDetail.do")
		public String buyerView(
				@RequestParam("buyerId") String buyerId,
				HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			int status = 200;
			String msg = null;
			
			BuyerVO buyer = null;
			
			try {
				buyer = service.retrieceBuyer(buyerId);
			}catch(DataNotFoundException e) {
				status = 500;
				msg = "데이터 없음";
			}
			
			if(status == 200) {
				req.setAttribute("buyer", buyer);
				return "/buyer/buyerDetail";
			}else {
				resp.sendError(status,msg);
				return null;
			}

		
		
	}
	
}
