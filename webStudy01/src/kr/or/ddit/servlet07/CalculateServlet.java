package kr.or.ddit.servlet07;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.CalculateVO;
import kr.or.ddit.vo.OperatorInfoVO;
import kr.or.ddit.vo.OperatorVO;

@WebServlet("/calculate")
public class CalculateServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		OperatorInfoVO infoVO = null;
		// xml파일 언 마샬링
		try(
				InputStream is = getClass().getResourceAsStream("../operator.xml");
			){
			JAXBContext jaxbContext = JAXBContext.newInstance(OperatorInfoVO.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			infoVO = (OperatorInfoVO) unmarshaller.unmarshal(is);
			
		}catch (Exception e) {
			throw new ServletException(e);
		}
		List<OperatorVO> list = infoVO.getOperatorList();
		
		// json형태로 변환
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list);
		
		try(
			PrintWriter out = resp.getWriter();
		){
			out.write(json);
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String leftStr = req.getParameter("left");
		String rightStr = req.getParameter("right");
		String operator = req.getParameter("operator");
		String accept = req.getParameter("mime");
		
		System.out.println(leftStr);
		System.out.println(rightStr);
		
	
		int status = 200;
		String errMsg = null;
		
		if(accept == null || accept.isEmpty()) {
			accept = req.getHeader("accept");
		}
		
		
		if(leftStr == null || !leftStr.matches("[0-9]+") || rightStr == null || !rightStr.matches("^[0-9]+$")) {
			status = 400;
			errMsg = "잘못된 파라미터 입니다";
		}else {
			int left = Integer.parseInt(leftStr);
			int right = Integer.parseInt(rightStr);
			
			CalculateVO calVO = new CalculateVO(left, right, operator);
			try{
				getResult(calVO, accept, req);
			}catch (Exception e) {
				throw new ServletException(e);
			}
			
			
		}
		
		
		if(status == 200) {
			Object contentType = req.getAttribute("contentType");
			resp.setContentType(Objects.toString(contentType,"text/html"));
			try(
				PrintWriter out = resp.getWriter();
			){
				Object obj = req.getAttribute("obj");
				System.out.println(obj);
				out.print(obj);
			}
			
		}else {
			resp.sendError(status,errMsg);
			return;
		}
		
		
	
	}


	private void getResult(CalculateVO calVO, String accept, HttpServletRequest req) throws JsonProcessingException {
		Object obj = null;
		String contentType = null;
		
		if(accept.contains("json")) {
			contentType = "application/json;charset=UTF-8";
			ObjectMapper mapper = new ObjectMapper();
			obj = mapper.writeValueAsString(calVO);
		}else {
			contentType = "text/plain;charset=UTF-8";
			obj = calVO.getExpression();
		}
		
		req.setAttribute("obj", obj);
		req.setAttribute("contentType", contentType);
		
	}
	
	

	
}









