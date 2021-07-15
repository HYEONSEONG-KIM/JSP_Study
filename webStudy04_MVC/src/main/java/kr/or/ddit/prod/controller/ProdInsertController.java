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
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.resolvers.ModelAttribute;
import kr.or.ddit.mvc.resolvers.RequestPart;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdInsertController{
	
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	Logger logger = LoggerFactory.getLogger(ProdInsertController.class);
	
	@RequestMapping("/prod/prodInsert.do")
	public String form(HttpServletRequest req) {
		
		
		List<Map<String, Object>> prodLguList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList = othersDAO.selectBuyerList();
		
		req.setAttribute("prodLguList", prodLguList);
		req.setAttribute("buyerList", buyerList);
		
		return "prod/prodInsert";
		
		
		
	}
	
	
	@RequestMapping(value = "/prod/prodInsert.do" , method = RequestMethod.POST)
	public String prodInsert(@ModelAttribute("prod") ProdVO prod,
			@RequestPart("prodImage") MultipartFile prodImage, HttpServletRequest req) {


			prod.setProdImage(prodImage);
		
		
		
		Map<String, String> errors = new HashMap<String, String>();		
		boolean valid = validate(prod, errors);
		System.out.println("valid" + valid);
		req.setAttribute("errors", errors);
		
		
		
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
				message = "등록에 실패하였습니다. 잠시 후 다시 시도해 주세요";
				req.setAttribute("message", message);
				break;
			}
			
		}
		
		
		return viewName;
		
		
	}


	private boolean validate(ProdVO prod, Map<String, String> errors) {
		
		boolean valid = true;
		
		
		if (StringUtils.isBlank(prod.getProdName())) {
			valid = false;
			errors.put("prodName", "상품 이름 누락");
		}
		if (StringUtils.isBlank(prod.getProdLgu())) {
			valid = false;
			errors.put("prodLgu", "상품 분류코드 누락");
		}
		if (StringUtils.isBlank(prod.getProdBuyer())) {
			valid = false;
			errors.put("prodBuyer", "거래처 코드 누락");
		}
	/*	if (prod.getProdCost() == 0) {
			valid = false;
			errors.put("prodCost", "구매가격 누락");
		}
		if (prod.getProdPrice() == 0) {
			valid = false;
			errors.put("prodPrice", "가격 누락");
		}
		if (prod.getProdSale() == 0) {
			valid = false;
			errors.put("prodSale", "판매가격 누락");
		}*/
		if (StringUtils.isBlank(prod.getProdOutline())) {
			valid = false;
			errors.put("prodOutline", "간단정보 누락");
		}
		if (StringUtils.isBlank(prod.getProdImg())) {
			valid = false;
			errors.put("prodImg", "이미지 누락");
		}
	/*	if (prod.getProdTotalstock() == 0) {
			valid = false;
			errors.put("prodTotalstock", "총재고 누락");
		}
		if (prod.getProdProperstock() ==0) {
			valid = false;
			errors.put("prodProperstock", "상품재고 누락");
		}*/
		return valid;
	}

}


















