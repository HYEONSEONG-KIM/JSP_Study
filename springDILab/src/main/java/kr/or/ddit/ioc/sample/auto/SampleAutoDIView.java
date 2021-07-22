package kr.or.ddit.ioc.sample.auto;

import java.util.Arrays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.ioc.sample.service.ISampleService;
import kr.or.ddit.ioc.sample.service.SampleServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleAutoDIView {
	
	public static void main(String[] args) {
		try(
			ConfigurableApplicationContext context =
				new GenericXmlApplicationContext("classpath:kr/or/ddit/ioc/sample/conf/autoDI-context.xml");
		){
			int beanCount = context.getBeanDefinitionCount();
			log.info("등록된 빈의 갯수 : {}", beanCount);
			String[] beanNames = context.getBeanDefinitionNames();
			Arrays.stream(beanNames).forEach((beanName)->{
				System.out.println(beanName);
			});
			ISampleService service = context.getBean(SampleServiceImpl.class);
			StringBuffer content = service.retrieveById("a001");
			log.info("contents : {}", content);
		}
		
		
	}
}
