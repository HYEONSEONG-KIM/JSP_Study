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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	
	@GetMapping
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String viewName = "prod/prodInsert";
		String prodId = req.getParameter("what");
		
		ProdVO prod = null;
		
	
		
		if(StringUtils.isBlank(prodId)) {
			resp.sendError(400,"필수 파라미터 누락");
			return;
		}
		
		
		try {
			prod = service.retrieveProd(prodId);
		}catch (DataNotFoundException e) {
			resp.sendError(500,e.getMessage());
		}
		
		
		List<Map<String, Object>> prodLguList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList = othersDAO.selectBuyerList();
		
		req.setAttribute("prod", prod);
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
	
	@PostMapping
	protected void doPost(
			@RequestPart(value = "prodImage",required = false) MultipartFile prodImage,
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProdVO prod = new ProdVO();
		
			prod.setProdImage(prodImage);
		
		
		try {
			BeanUtils.populate(prod, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(prod.getProdId());
		List<Map<String, Object>> prodLguList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList = othersDAO.selectBuyerList();
		
		Map<String, List<String>> errors = new HashMap<String, List<String>>();
		
		req.setAttribute("prod", prod);
		req.setAttribute("prodLguList", prodLguList);
		req.setAttribute("buyerList", buyerList);
		req.setAttribute("errors", errors);
		
		
		ValidatorUtils<ProdVO> utils = new ValidatorUtils<>();
		
		boolean valid = utils.validate(prod, errors, UpdateGroup.class);
		
		String viewName = null;
		String message = null;
		
		if(!valid) {
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

















