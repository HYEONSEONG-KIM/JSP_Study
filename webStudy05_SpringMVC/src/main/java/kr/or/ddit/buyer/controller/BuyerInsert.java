/*package kr.or.ddit.buyer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.buyer.service.buyerService;
import kr.or.ddit.buyer.service.buyerServiceImpl;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BuyerVO;

@Controller
public class BuyerInsert {

	private OthersDAO otherDao = new OthersDAOImpl();
	private buyerService service = buyerServiceImpl.getInstance();
	
	private void getLprodList(HttpServletRequest req) {
		List<Map<String, Object>> lprodList = otherDao.selectLprodList();
		req.setAttribute("lprodList", lprodList);
	}

	@RequestMapping("/buyer/buyerInsert.do")
	public String insertForm(HttpServletRequest req) {
		
		getLprodList(req);
		return "buyer/buyerForm";
	}

	@RequestMapping(value = "/buyer/buyerInsert.do", method = RequestMethod.POST)
	public String buyerInsert(
			@ModelAttribute("buyer") BuyerVO buyer
			,@RequestPart("buyerImage") MultipartFile buyerImage
			,HttpServletRequest req) {
		
		buyer.setBuyerImage(buyerImage);
		
		getLprodList(req);
		Map<String,List<String>> errors = new HashMap<>();
		
		ValidatorUtils<BuyerVO> utils = new ValidatorUtils<>();
		
		boolean valid = utils.validate(buyer, errors, InsertGroup.class);
		String viewName = null;
		String message = null;
		
		req.setAttribute("errors", errors);
		
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
		
		return viewName;
		
	
	}

	
}
*/