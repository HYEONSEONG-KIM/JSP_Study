package kr.or.ddit.various;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionDIView {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new GenericXmlApplicationContext("classpath:kr/or/ddit/various/Collection-DI.xml");
		context.registerShutdownHook();
	}
}
