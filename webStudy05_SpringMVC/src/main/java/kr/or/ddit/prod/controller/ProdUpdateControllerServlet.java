package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;


@Controller
@RequestMapping("/prod/prodUpdate.do")
public class ProdUpdateControllerServlet {
	
	@Inject
	private ProdService service;
	@Inject
	private OthersDAO othersDAO;
	
	
	@ModelAttribute("prodLguList")
	public List<Map<String, Object>> prodLguList(){
		List<Map<String, Object>> prodLguList = othersDAO.selectLprodList();
		return prodLguList;
	}
	
	@ModelAttribute("buyerList")
	public List<BuyerVO> buyerList(){
		List<BuyerVO> buyerList = othersDAO.selectBuyerList();
		return buyerList;
	}
	
	
	
	@GetMapping
	protected String doGet
		(	@RequestParam("what") String prodId,
			Model model
			){
		
		
		String viewName = "prod/prodInsert";
		
		ProdVO prod = service.retrieveProd(prodId);
	
	
		model.addAttribute("prod", prod);
		
		return viewName;
		
	}
	
	@PostMapping
	protected String doPost(
			@Validated(UpdateGroup.class) @ModelAttribute("prod") ProdVO prod,
			Errors errors,
			HttpServletRequest req, 
			HttpServletResponse resp
			) throws ServletException, IOException {
		
		
		req.setAttribute("prod", prod);
		
		req.setAttribute("errors", errors);
		
		
		String viewName = null;
		String message = null;
		
		if(errors.hasErrors()) {
			viewName = "prod/prodInsert"; 
		}else {
			try {
				ServiceResult result = service.modifyProd(prod);
				
				if(result == ServiceResult.OK) {
					viewName = "redirect:/prod/prodView.do?what=" + prod.getProdId();
				}else {
					viewName = "prod/prodInsert"; 
					message = "수정에 실패 하였습니다. 잠시 후 다시 시도하여 주세요";
				}
				
			}catch (DataNotFoundException e) {
				viewName = "prod/prodInsert";
				message = "데이터를 찾을 수 없습니다";
			}
			
		}
		
		return viewName;
	
		
	}
}

















