package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.controller.MemberListController;
import kr.or.ddit.member.controller.MemberViewController;
import kr.or.ddit.mvc.annotation.HandlerAdapter;
import kr.or.ddit.mvc.annotation.HandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingInfo;
import kr.or.ddit.mvc.resolvers.RequestParamArgumentResolver;
import kr.or.ddit.mvc.resolvers.RequestPartArgumentResolver;
import kr.or.ddit.prod.controller.ProdRetrieveContorller;

public class FrontController extends HttpServlet{

	private HandlerMapping handlerMapping;
	private HandlerAdapter handlerAdapter;
	private ViewResolver viewResolver;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// 하드코딩하지 않게 web.xml에 파라미터로 설정
		String basePackage = config.getInitParameter("basePackage");
		String prefix =  config.getInitParameter("prefix");
		String suffix =  config.getInitParameter("suffix");
		// basePackage에 안에 mapping정보를 map의 형태로 저장
		handlerMapping = new RequestMappingHandlerMapping(basePackage);
		handlerAdapter = new RequestMappingHandlerAdapter();
		((RequestMappingHandlerAdapter)handlerAdapter).addArgumentResolvers(new RequestPartArgumentResolver());
		viewResolver = new InternalResourceViewResolver();
		((InternalResourceViewResolver)viewResolver).setPrefix(prefix);
		((InternalResourceViewResolver)viewResolver).setSuffix(suffix);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 매핑할 정보
		RequestMappingInfo mappingInfo = handlerMapping.findCommandHandler(req);
		// 요청에 들어온 mapping주소가 없으면 null 
		if(mappingInfo == null) {
			resp.sendError(404, req.getRequestURI() + "에 대한 핸들러가 없음, 제공하지 않는 서비스");
			return;
		}
		String viewName = handlerAdapter.invokeHandler(mappingInfo, req, resp);
		if(viewName == null) {
			if(!resp.isCommitted()) {
				resp.sendError(500, "논리적인 뷰네임이 결정되지 않았음. 내잘못이요...ㅠㅠ");
			}
		}else {
			viewResolver.viewResolver(viewName, req, resp);
		}
	}
}
