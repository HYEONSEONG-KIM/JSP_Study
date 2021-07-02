package kr.or.ddit.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.emp.service.EmployeeService;
import kr.or.ddit.emp.service.EmployeeServiceImpl;
import kr.or.ddit.vo.EmployeeVO;

@WebServlet("/employee/empSelect.do")
public class EmployeeSelectControllerServlet extends HttpServlet{

	private EmployeeService service = EmployeeServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String accept = req.getHeader("accept");
		String empnoStr = req.getParameter("empno");
		int empno = 0;
		
		int status = 200;
		String msg = null;
		
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
		}else {
			resp.setContentType("text/html;charset=UTF-8");
		}
		
		if(StringUtils.isBlank(empnoStr)) {
			status = 400;
			msg = "잘못된 파라미터 입니다";
		}else {
			empno = Integer.parseInt(empnoStr);
		}
		
		EmployeeVO empVO = service.retrieveEmployee(empno);
		
		if(status == 200) {
			try(
				PrintWriter out = resp.getWriter();
			){
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(out, empVO);
			}
		}else {
			resp.sendError(status,msg);
		}
		
		
		
		
	}
}
