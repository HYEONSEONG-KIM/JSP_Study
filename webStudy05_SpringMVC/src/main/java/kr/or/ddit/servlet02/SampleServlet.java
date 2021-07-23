package kr.or.ddit.servlet02;

import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/01/sample.tmpl")
public class SampleServlet extends ReadTmplServlet{
	
	@Override
	protected void makeData(HttpServletRequest req) {
		req.setAttribute("today", new Date());
	}

	@Override
	protected String getMime() {
		return "text/html;charset=UTF-8";
	}
}
