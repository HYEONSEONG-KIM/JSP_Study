package kr.or.ddit.multipart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

// 필터를 통해 들어온 request객체가 mutipart형식의 요청이면 wrapper로 바꿔주기 위한 클래스
public class StandardMultipartHttpServletRequest extends HttpServletRequestWrapper{
	
	// key=>partName, value => part
	private Map<String, List<MultipartFile>> multipartFiles;
	
	public StandardMultipartHttpServletRequest(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		multipartFiles = new LinkedHashMap<>();
		parseRequest(request);
	}

	private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
		Collection<Part> parts = request.getParts();
		for(Part single : parts) {
			if(single.getContentType() == null) continue;
			MultipartFile file = new StandardMultipartFile(single);
			String partName = file.getName();
			
			// map에서 꺼낸후 null일 경우(반복문이 처음 돌아가는 경우) 객체 생성후 add
			List<MultipartFile> already = multipartFiles.get(partName);
			if(already == null) {
				already = new ArrayList<MultipartFile>();
				multipartFiles.put(partName, already);
			}
			already.add(file);
		}
		
	}
	
	// map에 key값에 해당하는 part가 하나 일경우 사용하는 메서드
	public MultipartFile getFile(String partName) {
		List<MultipartFile> files = multipartFiles.get(partName);
		MultipartFile file = null;
		if(files != null && !files.isEmpty()) {
			file = files.get(0);
		}
		return file;
	}
	
	// 여러개일 경우 사용하는 메서드
	public List<MultipartFile> getFiles(String partName){
		return multipartFiles.get(partName);
	}
	
	// map 전체를 반환하는 메서드
	public Map<String, List<MultipartFile>> getMultipartFiles() {
		return multipartFiles;
	}
	
	
	/**
	 * map안에 키값을 가져오기 위한 메서드
	 * @return
	 */
	public Enumeration<String> getPartNames() {
		// collection view
		// set을 마치 순서가 있는것 처럼 사용
		Iterator<String> keys = multipartFiles.keySet().iterator();
		
		return new Enumeration<String>() {
			@Override
			public boolean hasMoreElements() {
				return keys.hasNext();
			}
			
			@Override
			public String nextElement() {
				return keys.next();
			}
		};
	}
}















