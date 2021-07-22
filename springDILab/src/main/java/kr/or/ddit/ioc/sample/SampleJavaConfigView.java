package kr.or.ddit.ioc.sample;

import java.util.Arrays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.ddit.ioc.sample.conf.SampleJavaConfigContainer;
import kr.or.ddit.ioc.sample.dao.ISampleDAO;
import kr.or.ddit.ioc.sample.service.ISampleService;

public class SampleJavaConfigView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new AnnotationConfigApplicationContext(SampleJavaConfigContainer.class);
	/*	String[] beanNames = context.getBeanDefinitionNames();
		Arrays.stream(beanNames).forEach((beanName)->{
				System.out.println(beanName);
			
		});*/
//		ISampleDAO dao1 = context.getBean(ISampleDAO.class);
//		ISampleDAO dao2 = context.getBean(ISampleDAO.class);
//		System.out.println(dao1 == dao2);
//		
//		ISampleService service1 = context.getBean("service2",ISampleService.class);
//		ISampleService service2 = context.getBean("service2",ISampleService.class);
//		System.out.println(service1 == service2);
//		System.out.println(service2.retrieveById("a001"));
	}
}
