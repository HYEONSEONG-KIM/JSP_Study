package kr.or.ddit.servlet02;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/01/gugudan.tmpl")
public class GugudanServlet extends ReadTmplServlet{

	@Override
	protected String getMime() {
		return "text/html;charset=utf-8";
	}

	@Override
	protected void makeData(HttpServletRequest req) {
		
		String minStr = req.getParameter("minDan");
		String maxStr = req.getParameter("maxDan");
		
		
		int minDan = 2;
		if(minStr != null && minStr.matches("\\d{1,2}")) {
			minDan = Integer.parseInt(minStr);
		}
		int maxDan = 9;
		if(maxStr != null && maxStr.matches("[0-9]+")) {
			maxDan = Integer.parseInt(maxStr);
		}
		
		StringBuffer gugudan = new StringBuffer();
		
		
		for(int i = minDan; i <= maxDan; i ++) {
			gugudan.append("<tr><td>"+i + "단</td>");
			for(int j = 1; j <= 9; j++) {
				String result = Integer.toString(i *j);
				gugudan.append("<td>" + i + " * " + j + " = " + result + "</td>");
			}
			gugudan.append("</tr>");
		}
		
	
		req.setAttribute("gugudan", gugudan);
	}

}
