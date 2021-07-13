package kr.or.ddit.fileupload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *	파일을 포함한 다중 데이터 처리(multipart request)
 *	1. 멀티 파트 요철을 받을 수 있는 MultiPartConfig 설정이 추가된 서블릿 구현
 *	2. servlet 3.0 부터 추가된 Part API 활용
 *		req.getPart(partName), req.getParts()
 *		- req.getParts() 사용하는 경우
 *			text part와 file part를 분리하여 처리
 *	3. file part에 대한 업로드 처리
 *		1) 파일 저장 위치
 *		2) 저장 파일명(파일명 정책 결정)
 *		3) Part로 부터 확보한 입력 스트림과 1차 출력 스트림으로 스트림 복사
 *
 */

/*@WebServlet("/fileUpload.do")
@MultipartConfig*/
public class FileUploadServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String textParam = req.getParameter("textParam");
		req.getSession().setAttribute("textParam", textParam);
		Part filePart = req.getPart("filePart");
		
		System.out.println(textParam);
		//System.out.println(filePart);
		System.out.println(req.getParts().size());
		
		String saveFolderUrl = "/resources/images";
		String saveFolderPath = req.getServletContext().getRealPath(saveFolderUrl);
		
		File saveFolder = new File(saveFolderPath);
		boolean empty = filePart.getSize() == 0;
		if(!empty) {
			if(StringUtils.startsWith(filePart.getContentType(), "image/")) {
				//Content-Disposition: form-data; name="filePart"; filename=""
				String disposition = filePart.getHeader("Content-Disposition");
				String originalFilename = extractFileName(disposition);
				String savename = UUID.randomUUID().toString();
				
				File saveFile = new File(saveFolder, savename);
				try(
					InputStream is = filePart.getInputStream();
				){
					FileUtils.copyInputStreamToFile(is, saveFile);
					
					req.getSession().setAttribute("imageURL", saveFolderUrl + "/" + saveFile.getName());
				}
			}
		}
		
		resp.sendRedirect(req.getContextPath() + "/13/fileUploadForm.jsp");
	}

	private String extractFileName(String disposition) {
		//form-data; name="filePart"; filename=""
		if(disposition == null || disposition.isEmpty()) {
			return null;
		}
		
		int first = disposition.indexOf("filename=");
		String filename = null;
		
		if(first != -1) {
			int end = disposition.indexOf(";",first);
			if(end == -1) {
				filename = disposition.substring(first + "filename=".length());
			}else {
				filename = disposition.substring(first + "filename=".length(), end);
			}
			filename = filename.replace("\"", "");
		}
		
		return filename;
	}
}







