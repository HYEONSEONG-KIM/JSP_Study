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
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.vo.BuyerVO;

@WebServlet("/buyer/buyerDetail.do")
public class buyerSelectControllerServlet extends HttpServlet{

	private buyerService service = buyerServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String buyerId = req.getParameter("buyerId");
		
		int status = 200;
		String msg = null;
		
		if(StringUtils.isBlank(buyerId)) {
			status = 400;
			msg = "잘못된 파라미터";
		}

		BuyerVO buyer = null;
		
		try {
			buyer = service.retrieceBuyer(buyerId);
		}catch(DataNotFoundException e) {
			status = 500;
			msg = "데이터 없음";
		}
		
		if(status == 200) {
			req.setAttribute("buyer", buyer);
			req.getRequestDispatcher("/WEB-INF/views/buyer/buyerDetail.jsp").forward(req, resp);
		}else {
			resp.sendError(status,msg);
		}
		
		
		
	
	}
}
