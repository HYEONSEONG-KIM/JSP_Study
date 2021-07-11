package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.service.buyerService;
import kr.or.ddit.buyer.service.buyerServiceImpl;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.vo.BuyerVO;

@WebServlet("/buyer/buyerInsert.do")
public class BuyerInsertControllerServlet extends HttpServlet {

	private OthersDAO otherDao = new OthersDAOImpl();
	private buyerService service = buyerServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Map<String, Object>> lprodList = otherDao.selectLprodList();
		req.setAttribute("lprodList", lprodList);

		String viewName = "buyer/buyerForm";

		if (viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);

		} else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		BuyerVO buyer = new BuyerVO();
		Map<String,String> errors = new HashMap<String, String>();
		List<Map<String, Object>> lprodList = otherDao.selectLprodList();
		
		try {
			BeanUtils.populate(buyer, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean valid = validation(buyer, errors); 
		String viewName = null;
		
		String message = null;
		
		req.setAttribute("errors", errors);
		req.setAttribute("buyer", buyer);
		req.setAttribute("lprodList", lprodList);
		
		
		if(!valid) {
			viewName = "buyer/buyerForm";
		}else {
			
			ServiceResult result = service.createBuyer(buyer);
			
			if(result == ServiceResult.OK) {
				viewName = "redirect:/buyer/buyerList.do";
			}else {
				viewName = "buyer/buyerForm";
				
				message = "등록에 실패하였습니다. 잠시 후 다시 이용하여 주세요";
				req.setAttribute("message", message);
			}
			
		}
		
		
		if (viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);

		} else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
		
	
	}

	private boolean validation(BuyerVO buyer, Map<String, String> errors) {

		boolean valid = true;
		
		if (StringUtils.isBlank(buyer.getBuyerName())) {
			valid = false;
			errors.put("buyerName", "거래처명 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyerLgu())) {
			valid = false;
			errors.put("buyerLgu", "상품분류코드 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyerComtel())) {
			valid = false;
			errors.put("buyerComtel", "회사번호 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyerFax())) {
			valid = false;
			errors.put("buyerFax", "팩스번호 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyerMail())) {
			valid = false;
			errors.put("buyerMail", "메일 누락");
		}

		return valid;
	}
}
