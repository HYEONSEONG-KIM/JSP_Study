package kr.or.ddit.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.board.service.FreeBoardServiceImpl;
import kr.or.ddit.enumtype.BrowserType;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.resolvers.RequestParam;
import kr.or.ddit.vo.AttatchVO;

@Controller
public class FreeBoardDownloadController {
	
	private FreeBoardService service = FreeBoardServiceImpl.getInstance();
	private static final String DISPOSITION = "Content-Disposition";
	
	@RequestMapping("/board/download.do")
	public String download(
			@RequestParam("what") int attNo,
			HttpServletResponse resp,
			HttpServletRequest req
			) throws ServletException, IOException{
		
		AttatchVO attatch = service.download(attNo);
		
		String saveFileName = attatch.getAttSavename();
		// 업로드할 파일이 저장되어있는 폴더 경로
		String uploadPath = "C:/saveFiles";
		// 업로드 하고자하는 파일명(실제 폴더에 저장된 이름)
		String filePath = uploadPath + File.separator + saveFileName;
		
		File uploadFile = new File(filePath);
		
		
		if(uploadFile.exists()) {
			String userAgent = req.getHeader("User-Agent");
			BrowserType browserType = BrowserType.findBrowser(userAgent);
			String filename = attatch.getAttFilename();
			switch (browserType) {
			case MSIE:
			case TRIDENT:
				filename = URLEncoder.encode(filename, "UTF-8");
				filename = filename.replace("+", " ");
				break;
			default:
				byte[] bytes = filename.getBytes();
				filename = new String(bytes,"ISO-8859-1");
				break;
			}
			
			resp.setContentType("application/octet-stream; charset=UTF-8");
			resp.setHeader("Content-Length", attatch.getAttFilesize()+"");
			// 실제 파일의 이름
			
		
			String headerValue = "attachment; filename=\"" + filename + "\"";
			resp.setHeader(DISPOSITION, headerValue);
			
			try(
				OutputStream os = resp.getOutputStream();
			){
				FileUtils.copyFile(uploadFile, os);
			}
			
		}else {
			resp.sendError(404,"파일이 존재하지 않음");
		}
		
		return null;
	}
}










