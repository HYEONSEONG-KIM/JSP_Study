package kr.or.ddit.prod.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodInsert.do")
public class ProdInsertController{
	
	@Inject
	private ProdService service ;
	@Inject
	private OthersDAO othersDAO ;
	
	Logger logger = LoggerFactory.getLogger(ProdInsertController.class);
	
	@GetMapping
	public String form(HttpServletRequest req) {
		
		
		List<Map<String, Object>> prodLguList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList = othersDAO.selectBuyerList();
		
		req.setAttribute("prodLguList", prodLguList);
		req.setAttribute("buyerList", buyerList);
		
		return "prod/prodInsert";
		
		
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String prodInsert(
			@Validated(InsertGroup.class) @ModelAttribute("prod") ProdVO prod,
			Errors errors,
			HttpServletRequest req) {


		
		
		
		
		
		
		String viewName = null;
		String message = null;
		
		System.out.println(prod);
		
		if(errors.hasErrors()) {
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


















