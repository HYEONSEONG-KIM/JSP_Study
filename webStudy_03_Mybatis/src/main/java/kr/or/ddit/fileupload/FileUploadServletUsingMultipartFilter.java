package kr.or.ddit.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.multipart.StandardMultipartHttpServletRequest;

@WebServlet("/fileUploadUsingFilter.do")
@MultipartConfig
public class FileUploadServletUsingMultipartFilter extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String textParam = req.getParameter("textParam");
		req.getSession().setAttribute("textParam", textParam);
		// req가 원본인지 wrapper인지 검증
		if(req instanceof StandardMultipartHttpServletRequest) {
			StandardMultipartHttpServletRequest wrapper = (StandardMultipartHttpServletRequest)req;
			// filePart => name으로 설정한 값(input태그에서)
			MultipartFile file = wrapper.getFile("filePart");
			if(file != null && !file.isEmpty()) {
				String contentType = file.getContentType();
				if(contentType.startsWith("image/")) {
					// 파일을 저장할 경로 설정
					String saveFolderUrl = "/resources/images";
					String saveFolderPath = req.getServletContext().getRealPath(saveFolderUrl);
					File saveFolder = new File(saveFolderPath);
					// 파일이름 랜덤으로 설정
					String savename = UUID.randomUUID().toString();
					File saveFile = new File(saveFolder, savename);
					
					file.transferTo(saveFile);
					
					req.getSession().setAttribute("imageFile", file);
					req.getSession().setAttribute("imageURL", saveFolderUrl + "/" + saveFile.getName());
				} // end if
			} // end if(file != null)
		}
		
		resp.sendRedirect(req.getContextPath() + "/13/fileUploadForm.jsp");
		
		
	}
}
