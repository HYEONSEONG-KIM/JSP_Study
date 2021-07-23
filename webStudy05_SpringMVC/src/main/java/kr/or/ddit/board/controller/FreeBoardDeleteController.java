package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.board.service.FreeBoardServiceImpl;
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.FreeBoardVO;

@Controller
public class FreeBoardDeleteController {

	@Inject
	private FreeBoardService service;
	
	@RequestMapping(value = "/board/boardDelete.do", method = RequestMethod.POST)
	public String deleteBoard(
			@ModelAttribute("board") FreeBoardVO board,
			RedirectAttributes redirectAttribute
			)throws ServletException, IOException {
		
		//Map<String, Object> jsonMap = new HashMap<String, Object>();
		String viewName = null;
		String message = null;
		try {
			ServiceResult result= service.removeBoard(board);
			//jsonMap.put("result", restult);
			switch (result) {
			case OK:
				viewName = "redirect:/board/boardList.do";
				break;

			case INVALIDPASSWORD:
				viewName = "redirect:/board/boardSelect.do?boNo=" + board.getBoNo();
				message = "비밀번호 오류";
				break;
				
			default:
				viewName = "redirect:/board/boardSelect.do?boNo=" + board.getBoNo();
				message = "서버오류";
				break;
			}
			
		}catch (DataNotFoundException e) {
			throw new ServletException(e);
		}
		
		/*ObjectMapper mapper = new ObjectMapper();
		
		try(
			PrintWriter out = resp.getWriter();
		){
			mapper.writeValue(out, jsonMap);
		}*/
		
		redirectAttribute.addFlashAttribute("message", message);
		return viewName;
	}
}
