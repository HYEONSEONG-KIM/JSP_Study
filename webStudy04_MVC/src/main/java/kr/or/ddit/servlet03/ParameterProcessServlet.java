package kr.or.ddit.servlet03;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/03/parameterProcess")
public class ParameterProcessServlet extends HttpServlet{

		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			System.out.printf("현재 요청 메서드 : %s\n", req.getMethod());
			Map<String, String[]> paramerterMap = req.getParameterMap();
			for(Entry<String, String[]> entry : paramerterMap.entrySet()) {
				String paramName = entry.getKey();
				String[] paramValue = entry.getValue();
				System.out.printf("%s : %s \n", paramName, Arrays.toString(paramValue));
			}
		}
		

}
