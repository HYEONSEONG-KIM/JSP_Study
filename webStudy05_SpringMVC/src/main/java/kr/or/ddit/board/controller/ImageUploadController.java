package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ImageUploadController {
	
	// servletContext에 대한 레퍼런스 가짐
	@Inject
	private WebApplicationContext container;
	private ServletContext application;
	private File saveFolder;
	
	@Value("#{appInfo.boardImageUrl}")
	private String saveFolderUrl;

	@PostConstruct
	public void init() {
		this.application = container.getServletContext();
		String saveFolderPath = application.getRealPath(saveFolderUrl);
		saveFolder = new File(saveFolderPath);
		
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		log.info("게시글 이미지 경로 (url): {}, \n실제 경로(path) : {}", saveFolderUrl, saveFolder.getAbsolutePath());
	}
	
	@RequestMapping(value = "/board/uploadImage.do", method = RequestMethod.POST, produces="application/json; charset=UTF-8")
	//produces accept가 이 마임으로 들어옴, 컨텐트타입이 이걸로 나가함
	@ResponseBody // 알아서 직렬화와 마샬링 수행
	public Map<String, Object> uploadImage(
			@RequestPart("upload") MultipartFile uploadImage
			) throws ServletException, IOException {
		Map<String, Object> result = new HashMap<>();
		
		if(!uploadImage.isEmpty()) {
		
			String saveName =UUID.randomUUID().toString();
			File saveFile = new File(saveFolder, saveName);
			uploadImage.transferTo(saveFile);
			
			String saveFileUrl = application.getContextPath() + saveFolderUrl + "/" + saveName;
			
			result.put("uploaded",1);
			result.put("fileName", uploadImage.getOriginalFilename());
			result.put("url",saveFileUrl );
		}
		
		return result;
	}
}
