package kr.or.ddit.enumtype;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public enum MimeType {
	// 각 상수의 정확한 개념은???하나하나가 객체 자기타입의 인스턴스!!!
	JSON("application/json;charset=UTF-8"), 
	SCRIPT("text/javascript;charset=UTF-8"), 
	PLAIN("text/plain;charset=UTF-8"), 
	HTML("text/html;charset=UTF-8");
	
	private String mimeText;
	
	
	
	private MimeType(String mimeText) {
		this.mimeText = mimeText;
	}



	public String getMimeText() {
		return mimeText;
	}
	
	public static MimeType findMimeType(String accept) {
		// enum의 객체로 선언한 변수에 해당객체의 상수를 받는다는 의미는??
		MimeType finded = MimeType.HTML;
		
		if(accept != null) {
			accept = accept.toUpperCase();
			for(MimeType tmp : values()) {
				// name은 정확히 무엇을 의미하는가??
				if(accept.indexOf(tmp.name())>=0) {
					finded = tmp;
					break;
				}
			}
		}
		
		return finded;
	}
	
	public static String findMimetext(String accept) {
		return findMimeType(accept).getMimeText();
	}
}




