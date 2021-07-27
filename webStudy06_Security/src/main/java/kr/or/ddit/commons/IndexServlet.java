package kr.or.ddit.commons;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.enumtype.ServiceType;
import kr.or.ddit.vo.MenuVO;
import kr.or.ddit.vo.ServiceInfoVO;

@Controller
public class IndexServlet implements ApplicationContextAware {
	
	private WebApplicationContext container;
	private ServletContext application;

	
	// 자기 자신에 대한 레퍼런스 넣어줌(Inject를 안넣어 준다면)
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.container = (WebApplicationContext) applicationContext;
		application = container.getServletContext();
	}
	
	


	@RequestMapping("/index.do")
	public String index(
		@RequestParam(required = false)	String service,
			Model model
			){

		ServiceInfoVO infoVO = (ServiceInfoVO) application.getAttribute("serviceInfo");

		List<MenuVO> menuList = infoVO.getMenuList();

		String contentPage = null;
		int status = 200;
		String errMsg = null;

		if (service == null || service.isEmpty()) {
			contentPage = "/WEB-INF/views/index.jsp";
		} else {
			MenuVO searchCondition = new MenuVO();
			searchCondition.setCode(service);
			int index = menuList.indexOf(searchCondition);

			if (index != -1) {
				MenuVO searched = menuList.get(index);
				contentPage = searched.getLink();
			}
		}
		// definitions에 el에 전달해 주기 위해
		model.addAttribute("contentsPage", contentPage);
		return "index";
		/*
		 * if(service == null || service.isEmpty()) { service = "INDEX"; }
		 * 
		 * String path = ServiceType.pathName(service);
		 * 
		 * if(path == null || path.isEmpty()) { resp.sendError(404,"존재하지 않는 페이지");
		 * return;
		 * 
		 * } String dest = "/WEB-INF/views/template.jsp";
		 * req.setAttribute("contentsPage", path);
		 * req.getRequestDispatcher(dest).forward(req, resp);;
		 */

	}

	
}
