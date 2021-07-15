package kr.or.ddit.servlet07;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumtype.BtsType;

@WebServlet("/bts")
public class BtsServlet extends HttpServlet {
		private ServletContext application;
		@Override
		public void init(ServletConfig config) throws ServletException {
			super.init(config);
			application = getServletContext();
			Map<String, String[]> btsMap = new HashMap<>();
			btsMap.put("B001", new String[] {"RM", "rm.jsp"});
			btsMap.put("B002", new String[] {"지민", "jimin.jsp"});
			application.setAttribute("btsMap", btsMap);
		}

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/08/btsForm.jsp");
			rd.forward(req, resp);
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String memName = req.getParameter("btsMember");
			int status = HttpServletResponse.SC_OK;
			String errorMsg = null;
			String goPage = null;
			if(memName == null || memName.isEmpty()) {
				status = 400;
				errorMsg = "필수 파라미터 누락";
			}
			//String name = BtsType.findMember(memName);
			
			Map<String, String[]> btsMap =  (Map<String, String[]>) application.getAttribute("btsMap");
			if(!btsMap.containsKey(memName)) {
				status = 404;
				errorMsg = String.format("방탄에는 %s 멤버가 없음", memName);
			}else {
				Date searchTime = new Date();
				req.setAttribute("searchTime", searchTime);
				String[] info =	btsMap.get(memName);
				goPage = info[1];
			}
			
			
			if(status==HttpServletResponse.SC_OK) {
				goPage = "/WEB-INF/views/bts/" + goPage;
				req.getRequestDispatcher(goPage).forward(req, resp);
			}else {
				resp.sendError(status,errorMsg);
			}
		
		}
		
}
