package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.pagingVO;

@Controller
public class ProdRetrieveContorller{

	@Inject
	private ProdService service;
	@Inject
	private OthersDAO othersDAO;
	private static final Logger logger = LoggerFactory.getLogger(ProdRetrieveContorller.class);
	
	@RequestMapping(value = "/prod/prodList.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public pagingVO<ProdVO> listForAjax(
			@RequestParam(value="page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("detailSearch") ProdVO prod
			){
		
		pagingVO<ProdVO> paging = new pagingVO<>(5,3);
		
		paging.setDetailSearch(prod);
		paging.setCurrentPage(currentPage);
		
		service.retrieveProdList(paging);
	
		return paging;
		
	}
	
	
	@RequestMapping("/prod/prodList.do")
	public String prodList(
			@RequestParam(value="page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("detailSearch") ProdVO prod,
			Model model
			) {
		
		List<Map<String, Object>> prodLguList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList = othersDAO.selectBuyerList();
	
	
		model.addAttribute("prodLguList", prodLguList);
		model.addAttribute("buyerList", buyerList);
		
		return "prod/prodList";
		
	}
	
	
	
	@RequestMapping("/prod/prodView.do")
	public String prodView(@RequestParam("what") String prodId, Model model){
		
		ProdVO prod = service.retrieveProd(prodId);
		model.addAttribute("prod", prod);
		
		return "prod/prodView";
	
	}
	
	
	
}
