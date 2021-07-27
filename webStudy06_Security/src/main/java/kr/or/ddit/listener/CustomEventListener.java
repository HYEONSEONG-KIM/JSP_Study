package kr.or.ddit.listener;

import java.io.InputStream;
import java.util.LinkedHashMap;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ServiceInfoVO;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomEventListener {
	
	@EventListener(ContextRefreshedEvent.class)
	public void eventHandler(ContextRefreshedEvent event) {
		WebApplicationContext container =  (WebApplicationContext) event.getApplicationContext();
		ServletContext application = container.getServletContext();
		if(application.getAttribute("cPath")==null)
			application.setAttribute("cPath", application.getContextPath());
		if(application.getAttribute("serviceInfo") == null) {
			init(container);
			log.info("좌측 메뉴 구조 로딩");
		}
		if(application.getAttribute("currentUserList")==null) {
			application.setAttribute("currentUserList", new LinkedHashMap<String, MemberVO>());
		}
		log.info("시작된 컨테이너 : {}", container);
	}
	
	private void init(WebApplicationContext container)  {
		Resource res = container.getResource("classpath:kr/or/ddit/serviceInfo.xml");
		try (
			InputStream is = res.getInputStream();
				
		) {
			JAXBContext jaxbContext = JAXBContext.newInstance(ServiceInfoVO.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			ServiceInfoVO infoVO = (ServiceInfoVO) unmarshaller.unmarshal(is);
			container.getServletContext().setAttribute("serviceInfo", infoVO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
