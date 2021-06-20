package kr.or.ddit.join;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.capchar.ApiExamCaptchaImage;
import kr.or.ddit.capchar.ApiExamCaptchaNkey;


@WebServlet("/CapcharImage.do")
public class CapcharImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApiExamCaptchaNkey getKey = new ApiExamCaptchaNkey();
		String key = getKey.getImageKey();
		
		String keys = key.substring(key.indexOf(":")+2 , key.lastIndexOf("\""));
		System.out.println("key : " + key);
		System.out.println("key : " + keys);
		String dirPath = request.getServletContext().getRealPath("captchaImage") + "\\";
		//String dirPath = "C:/jspSpring/workspace/capchar/WebContent/captchaImage/";
		ApiExamCaptchaImage image = new ApiExamCaptchaImage();
		String fileName = image.getImageName(keys, dirPath);
		
		
		System.out.println("file : " + fileName);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", keys);
		map.put("fileName", fileName);
		
		Gson gson =  new Gson();
		String json = gson.toJson(map);
		
		try(
			PrintWriter out = response.getWriter();
		){
			out.write(json);
		}
		
		
	}


}
