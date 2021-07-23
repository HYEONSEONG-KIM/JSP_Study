package kr.or.ddit.listener;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

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
		log.info("시작된 컨테이너 : {}", container);
	}
}
