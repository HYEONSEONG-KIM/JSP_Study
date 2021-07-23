package kr.or.ddit.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.emp.service.EmployeeService;
import kr.or.ddit.emp.service.EmployeeServiceImpl;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.EmployeeVO;



@WebServlet("/employee/empUpdate.do")
public class EmployeeUpdateControllerServlet extends HttpServlet {
	
	private EmployeeService service = EmployeeServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		EmployeeVO empVO = new EmployeeVO();
		int status = 200;
		String msg = null;
		
		try {
			BeanUtils.populate(empVO, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			System.out.println(empVO);
			ServiceResult result = null;
		try {
			result = service.modifyEmployee(empVO);

			if(result == ServiceResult.FAIL) {
				status = 400;
				msg = "잘못된 입력값 입니다";
			}
		}catch (DataNotFoundException e) {
			status = 404;
			msg = e.getMessage();
		}
		
		
		if(status == 200) {
			try(
				PrintWriter out = resp.getWriter()
			){
				out.print("ok");
			}
			
		}else {
			resp.sendError(status,msg);
		}
	}
	
}
