package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.board.service.FreeBoardServiceImpl;
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.resolvers.ModelAttribute;
import kr.or.ddit.mvc.resolvers.RequestParam;
import kr.or.ddit.vo.FreeBoardVO;

@Controller
public class FreeBoardDeleteController {

	private FreeBoardService service = FreeBoardServiceImpl.getInstance();
	
	@RequestMapping(value = "/board/boardDelete.do", method = RequestMethod.POST)
	public String deleteBoard(
			@ModelAttribute("board") FreeBoardVO board,
			HttpSession session,
			HttpServletResponse resp
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
		
		session.setAttribute("message", message);
		return viewName;
	}
}
