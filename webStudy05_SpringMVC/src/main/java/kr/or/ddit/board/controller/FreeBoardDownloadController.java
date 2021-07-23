package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.board.service.FreeBoardServiceImpl;
import kr.or.ddit.enumtype.BrowserType;
import kr.or.ddit.vo.AttatchVO;

@Controller
public class FreeBoardDownloadController {
	
	@Inject
	private FreeBoardService service;

	
	@RequestMapping("/board/download.do")
	public String download(
			@RequestParam("what") int attNo,
			Model model
			) throws ServletException, IOException{
		
		AttatchVO attatch = service.download(attNo);
		model.addAttribute("attatch", attatch);
		// 하위 컨텍스트에 등록된 bean의 id
		return "downloadView";
	}
}










