package kr.or.ddit.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.emp.service.EmployeeService;
import kr.or.ddit.emp.service.EmployeeServiceImpl;
import kr.or.ddit.vo.EmployeeWrapper;

@WebServlet("/employee/empList.do")
public class EmployeeListControllerServlet extends HttpServlet {
	
	private EmployeeService service = EmployeeServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("accept");
		String mgr = req.getParameter("mgr");
		Map<String, Object> pMap = new HashMap<>();
		if(StringUtils.isNumeric(mgr)) {
			pMap.put("mgr", Integer.parseInt(mgr));
		}
		
		if(StringUtils.containsIgnoreCase(accept, "json")){
			List<EmployeeWrapper> wrapperList = service.retrieveEmployeeList(pMap);
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = resp.getWriter();
			){
				
				mapper.writeValue(out, wrapperList);
			}
		}else {
			String dest = "/WEB-INF/views/emp/empList.jsp";
			req.getRequestDispatcher(dest).forward(req, resp);
		}
	}
}
