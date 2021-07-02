package kr.or.ddit.servlet07;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.FactorialVO;

@WebServlet("/05/factorial")
public class FactorialServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String paramLeft =req.getParameter("left");
		String accept = req.getParameter("mime");
		
		
		if(accept == null || accept.isEmpty()) {
			accept = req.getHeader("accept");
		}
		
		int status = 200;
		if(paramLeft == null || !paramLeft.matches("[0-9]{1,2}")) {
			status = 400;
		}else {
			int left = Integer.parseInt(paramLeft);
			FactorialVO target = new FactorialVO(left);
			Object obj = null;
			try {
				generateContets(target, accept, req);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if(status ==200) {
			Object contentType = req.getAttribute("contentType");
			resp.setContentType(Objects.toString(contentType,"text/html"));
			try(
				PrintWriter out = resp.getWriter();
			){
				Object contents = req.getAttribute("contents");
				out.print(contents);
			}
			
		}else {
			resp.sendError(status);
		}
	
	}

	private void generateContets(FactorialVO target, String accept, HttpServletRequest req) throws Exception {
		Object contests = null;
		String contentType = null;
		if(accept.contains("json")) {
			contentType = "appliation/json;charset=UTF-8";
			ObjectMapper mapper = new ObjectMapper();
			contests = mapper.writeValueAsString(target);
			
		}else if(accept.contains("xml")) {
			contentType = "appliation/xml;charset=UTF-8";
			JAXBContext context = JAXBContext.newInstance(target.getClass());
			StringWriter writer = new StringWriter();
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(target, writer);
			contests = writer.toString();
		}else {
			contentType = "text/plain;charset=UTF-8";
			contests = target.getExpression();
		}
		req.setAttribute("contents", contests);
		req.setAttribute("contentType", contentType);
	}
}
