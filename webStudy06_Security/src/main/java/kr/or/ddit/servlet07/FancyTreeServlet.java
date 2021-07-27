package kr.or.ddit.servlet07;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fancytree.do")
public class FancyTreeServlet extends HttpServlet{
	ServletContext application;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		application = getServletContext();
		String path = application.getRealPath("");
		
		List<String> folderName = new ArrayList<>();
		List<String> fileName = null;
		Map<String, List<String>> fileMap = new HashMap<>();
		
		File file = new File(path);
		File[] files =  file.listFiles();
		File[] name = null;
		
		for(int i = 0; i < files.length; i++) {
			name = files[i].listFiles();
			
			
			fileName = new ArrayList<>();
		
			if(files[i].getName().matches("[0-9]+")) {
				folderName.add(files[i].getName());
				for(int j= 0; j < name.length; j++) {
					fileName.add(name[j].getName());
				}
				
				fileMap.put(files[i].getName(), fileName);
			}
		}
		
		req.setAttribute("folderName", folderName);
		req.setAttribute("fileName", fileName);
		req.setAttribute("fileMap", fileMap);
		
		req.getRequestDispatcher("/10/fancytree.jsp").forward(req, resp);
	}
	
	
	
	
}











