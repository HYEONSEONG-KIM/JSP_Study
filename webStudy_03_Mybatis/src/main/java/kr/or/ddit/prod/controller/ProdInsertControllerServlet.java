package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.multipart.StandardMultipartHttpServletRequest;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
@MultipartConfig()
public class ProdInsertControllerServlet extends HttpServlet{
	
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	Logger logger = LoggerFactory.getLogger(ProdInsertControllerServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String viewName = "prod/prodInsert";
		
		List<Map<String, Object>> prodLguList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList = othersDAO.selectBuyerList();
		
		req.setAttribute("prodLguList", prodLguList);
		req.setAttribute("buyerList", buyerList);
		
		
		
		if(viewName.startsWith("redirect:")) {
			viewName  = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
			
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProdVO prod = new ProdVO();
		if(req instanceof StandardMultipartHttpServletRequest) {
			MultipartFile prodImage = ((StandardMultipartHttpServletRequest) req).getFile("prodImage");
			prod.setProdImage(prodImage);
		}
		// ?????? ??????
		
		// ?????? ??????
		
		// ????????????
		
		try {
			BeanUtils.populate(prod, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		Map<String, String> errors = new HashMap<String, String>();		
		boolean valid = validate(prod, errors);
		System.out.println("valid" + valid);
		req.setAttribute("errors", errors);
		req.setAttribute("prod", prod);	
		
		
		String viewName = null;
		String message = null;
		
		System.out.println(prod);
		
		if(!valid) {
			viewName = "prod/prodInsert";
		}else {
			
			ServiceResult result = service.createProd(prod);
			
			switch (result) {
			case OK:
				viewName = "redirect:/prod/prodList.do";
				break;

			default:
				List<Map<String, Object>> prodLguList = othersDAO.selectLprodList();
				List<BuyerVO> buyerList = othersDAO.selectBuyerList();
				
				req.setAttribute("prodLguList", prodLguList);
				req.setAttribute("buyerList", buyerList);
				
				viewName = "prod/prodInsert";
				message = "????????? ?????????????????????. ?????? ??? ?????? ????????? ?????????";
				req.setAttribute("message", message);
				break;
			}
			
		}
		
		
		if(viewName.startsWith("redirect:")) {
			viewName  = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
			
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
		
		
	}


	private boolean validate(ProdVO prod, Map<String, String> errors) {
		
		boolean valid = true;
		
		
		if (StringUtils.isBlank(prod.getProdName())) {
			valid = false;
			errors.put("prodName", "?????? ?????? ??????");
		}
		if (StringUtils.isBlank(prod.getProdLgu())) {
			valid = false;
			errors.put("prodLgu", "?????? ???????????? ??????");
		}
		if (StringUtils.isBlank(prod.getProdBuyer())) {
			valid = false;
			errors.put("prodBuyer", "????????? ?????? ??????");
		}
	/*	if (prod.getProdCost() == 0) {
			valid = false;
			errors.put("prodCost", "???????????? ??????");
		}
		if (prod.getProdPrice() == 0) {
			valid = false;
			errors.put("prodPrice", "?????? ??????");
		}
		if (prod.getProdSale() == 0) {
			valid = false;
			errors.put("prodSale", "???????????? ??????");
		}*/
		if (StringUtils.isBlank(prod.getProdOutline())) {
			valid = false;
			errors.put("prodOutline", "???????????? ??????");
		}
		if (StringUtils.isBlank(prod.getProdImg())) {
			valid = false;
			errors.put("prodImg", "????????? ??????");
		}
	/*	if (prod.getProdTotalstock() == 0) {
			valid = false;
			errors.put("prodTotalstock", "????????? ??????");
		}
		if (prod.getProdProperstock() ==0) {
			valid = false;
			errors.put("prodProperstock", "???????????? ??????");
		}*/
		return valid;
	}

}


















