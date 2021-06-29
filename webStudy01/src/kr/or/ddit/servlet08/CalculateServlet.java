package kr.or.ddit.servlet08;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumtype.OperatorType;
import kr.or.ddit.vo.OperateVO;
import kr.or.ddit.vo.OperatorVO;

@WebServlet("/calculate.do")
public class CalculateServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// post요청시 request의 body가 생성-> 이때 데이터의 형식을 표현하는 것이 content-Type
		String contentType = req.getHeader("Content-Type");
		OperateVO operateVO = null;
		// accpet는 서버에 어떤 형식으로 달라는 요청이 담겨있음
		String accept = req.getHeader("Accept");
		
		if(contentType != null && contentType.contains("json")) {
			operateVO = generateVOFromJson(req);
			
		}else {
			operateVO = generateVOFromParameter(req);
		}
		
		Object content = null;
		
		// vo객체가 json으로 mashaling이 될때는 자바 빈 규약에 의해 변환
		if(accept.contains("json")) {
			resp.setContentType("application/json;charset=utf-8");
			ObjectMapper mapper = new ObjectMapper();
			content = mapper.writeValueAsString(operateVO);
		}else {
			resp.setContentType("text/plain;charset=utf-8");
			content = operateVO.getExpression();
		}
		
		try(
			PrintWriter out = resp.getWriter();
		){
			out.print(content);
		}
		
	}

	private OperateVO generateVOFromParameter(HttpServletRequest req) {
		String leftParam = req.getParameter("left");
		String rightParam = req.getParameter("right");
		String operator = req.getParameter("operator");
		double left = Double.parseDouble(leftParam);
		double right = Double.parseDouble(rightParam);
		OperatorType operatorType = OperatorType.valueOf(operator);
		
		OperateVO operateVO = new OperateVO(left, right, operatorType);
		return operateVO;
	}

	private OperateVO generateVOFromJson(HttpServletRequest req) throws IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		try(
			InputStream is = req.getInputStream();
		){
			return mapper.readValue(is, OperateVO.class);
		}
		
	}
}






















