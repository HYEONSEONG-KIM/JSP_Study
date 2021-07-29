package kr.or.ddit.board.view;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.views.AbstractView;
import org.w3c.dom.views.DocumentView;

import kr.or.ddit.enumtype.BrowserType;
import kr.or.ddit.vo.AttatchVO;

public class DownloadView extends org.springframework.web.servlet.view.AbstractView {

	private static final String DISPOSITION = "Content-Disposition";
	//appInfo properties 파일에 파일 다운로드 할 경로 설정 후 사용
	@Value("#{appInfo.attatchPath}")
	private String uploadPath;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {

		AttatchVO attatch = (AttatchVO) model.get("attatch");
		String saveFileName = attatch.getAttSavename();
		// 업로드할 파일이 저장되어있는 폴더 경로
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
	}

	

}
