package kr.or.ddit.join;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.capchar.ApiExamCaptchaNkeyResult;


@WebServlet("/CapcharCheck.do")
public class CapcharCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		
		
		ApiExamCaptchaNkeyResult result = new ApiExamCaptchaNkeyResult();
		String res = result.check(key, val);
		
		res = res.substring(res.indexOf(":")+1, res.indexOf(","));
		
		System.out.println(key);
		System.out.println(val);
		System.out.println(res);
		
		Gson gson = new Gson();
		String json = gson.toJson(res);
		
		try(
		PrintWriter out = response.getWriter()
		){
			out.write(json);
			
		}
	}

}
