package kr.or.ddit.multipart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

// adapter 역할
public class StandardMultipartFile implements MultipartFile{
	
	private static final String DISPOSITION = "Content-Disposition";
	
	// 원하는 결과를 얻기위해 사용하는 기준(adaptee) 호출
	private final Part adaptee;

	public StandardMultipartFile(Part adaptee) {
		super();
		this.adaptee = adaptee;
	}
	
	// 파일의 이름을 가져오기 위한 메서드
	private String extractFileName(String disposition) {
		// 파일일 경우 disposition 형식 : form-data; name="filePart"; filename=""
		if(disposition == null || disposition.isEmpty()) {
			return null;
		}
		// filename을 기준으로 해당 disposition을 갖고있는 part가 파일인지 식별
		int first = disposition.indexOf("filename=");
		String filename = null;
		
		// 파일이 아니면 -1 반환
		if(first != -1) {
			int end = disposition.indexOf(";",first);
			if(end == -1) {
				filename = disposition.substring(first + "filename=".length());
			}else {
				filename = disposition.substring(first + "filename=".length(), end);
			}
			filename = filename.replace("\"", "");
		}
		// 자르고 잘라 정제하고 정제해서 원하는 파일이름 리턴...
		return filename;
	}
	

	@Override
	public String getOriginalFilename() {
		String disposition = this.adaptee.getHeader(DISPOSITION);
		return extractFileName(disposition);
	}

	@Override
	public boolean isEmpty() {
		return this.adaptee.getSize()==0;
	}

	@Override
	public String getName() {
		return this.adaptee.getName();
	}

	@Override
	public String getContentType() {
		return this.adaptee.getContentType();
	}

	@Override
	public long getSize() {
		return this.adaptee.getSize();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return this.adaptee.getInputStream();
	}

	@Override
	public byte[] getBytes() throws IOException {
		return IOUtils.toByteArray(getInputStream());
	}

	@Override
	public void transferTo(File saveFile) throws IOException {
		this.adaptee.write(saveFile.getPath());
		
	}
	
	
}
