package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.board.service.FreeBoardService.CountType;
import kr.or.ddit.board.service.FreeBoardServiceImpl;
import kr.or.ddit.enumtype.ServiceResult;

@Controller
public class FreeBoardIncrementContoller {

	@Inject
	private FreeBoardService service;
	
	@RequestMapping(value = "/board/increment.do", method= RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> Increment(
			@RequestParam("bono") int boNo,
			@RequestParam("type") String type
		){
		
		System.out.println("test");
		String viewName = null;
		ServiceResult result = service.incrementCount(boNo, CountType.valueOf(type.toUpperCase()));
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		resultMap.put("type", type);
	
		return resultMap;
		
		
	}
}
