package kr.or.ddit.commons;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import kr.or.ddit.enumtype.ServiceType;
import kr.or.ddit.vo.MenuVO;
import kr.or.ddit.vo.ServiceInfoVO;

@WebServlet(value = "/index.do", loadOnStartup=1)
public class IndexServlet extends HttpServlet {
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try(
			InputStream is = getClass().getResourceAsStream("../serviceInfo.xml");
		){
			application = getServletContext();
			JAXBContext jaxbContext = JAXBContext.newInstance(ServiceInfoVO.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			ServiceInfoVO infoVO =  (ServiceInfoVO) unmarshaller.unmarshal(is);
			application.setAttribute("serviceInfo", infoVO);
		}catch (Exception e) {
			throw new ServletException(e);
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String service = req.getParameter("service");
		ServiceInfoVO infoVO = (ServiceInfoVO) application.getAttribute("serviceInfo");
		
		List<MenuVO> menuList = infoVO.getMenuList();
		
		String contentPage = null;
		int status = 200;
		String errMsg = null;
		
		if(service == null || service.isEmpty()) {
			contentPage = "/WEB-INF/views/index.jsp";
		}else {
			MenuVO searchCondition = new MenuVO();
			searchCondition.setCode(service);
			int index = menuList.indexOf(searchCondition);
			
			if(index != -1) {
				MenuVO searched = menuList.get(index);
				contentPage = searched.getLink();
			}else {
				status = 404;
				errMsg = String.format("%s 서비스는 제공 불가", service);
			}
		}
		if(status == 200) {
			String dest = "/WEB-INF/views/template.jsp";
			req.setAttribute("contentsPage", contentPage);
			req.getRequestDispatcher(dest).forward(req, resp);
		}else {
			resp.sendError(status,errMsg);
		}
		
		
		/*if(service == null || service.isEmpty()) {
			service = "INDEX";
		}
		
		String path = ServiceType.pathName(service);
		
		if(path == null || path.isEmpty()) {
			resp.sendError(404,"존재하지 않는 페이지");
			return;
			
		}
		String dest = "/WEB-INF/views/template.jsp";
		req.setAttribute("contentsPage", path);
		req.getRequestDispatcher(dest).forward(req, resp);;
		*/
		
		
	}
}
