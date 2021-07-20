package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.board.service.FreeBoardService.CountType;
import kr.or.ddit.board.service.FreeBoardServiceImpl;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.resolvers.RequestParam;
import kr.or.ddit.vo.FreeBoardVO;

@Controller
public class FreeBoardIncrementContoller {

	private FreeBoardService service = FreeBoardServiceImpl.getInstance();
	
	@RequestMapping(value = "/board/increment.do", method= RequestMethod.POST)
	public String Increment(
			@RequestParam("bono") int boNo,
			@RequestParam("type") String type,
			HttpServletResponse resp
		)throws ServletException, IOException {
		
		System.out.println("test");
		String viewName = null;
		ServiceResult result = service.incrementCount(boNo, CountType.valueOf(type.toUpperCase()));
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		resultMap.put("type", type);
	
		
		ObjectMapper mapping = new ObjectMapper();
		
		try(
			PrintWriter out = resp.getWriter();
		){
			mapping.writeValue(out, resultMap);
		}
		
		
		
		return viewName;
	}
}
