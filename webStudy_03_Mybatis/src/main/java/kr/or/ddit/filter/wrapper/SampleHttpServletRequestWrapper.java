package kr.or.ddit.filter.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class SampleHttpServletRequestWrapper extends HttpServletRequestWrapper{
	
	private String customData = "커스텀 데이터";

	public SampleHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);

	}

	@Override
	public String getParameter(String name) {
		if("what".equals(name)) {
			return "P101000001";
		}else {
			return super.getParameter(name);
		}
			
	}
	
	public String getCustomData() {
		return customData;
	}
}
