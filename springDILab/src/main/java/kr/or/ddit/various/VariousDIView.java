package kr.or.ddit.various;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class VariousDIView {
	
	public static void main(String[] args) {
		//1. 주입받지 않으면 빈 등록 안되게 o => lazy-init
		//2. 주입 받을때 마다 새로운 객체가 만들어지는 구조로 o => scope
		//3. 주입이 다 끝낸 시점에 메세지 o => init
		//4. 설정파일을 가지고 주입 => 컨테이너 객체 다 끝나고 셧다운, 모든게 소멸됐는지 확인
		
		
		ConfigurableApplicationContext container = new GenericXmlApplicationContext("classpath:kr/or/ddit/various/Various-DI.xml");
		container.registerShutdownHook();
		
		VariousDIVO vo = container.getBean("vo1",VariousDIVO.class);
		VariousDIVO vo2 = container.getBean("vo1",VariousDIVO.class);
		VariousDIVO vo3 = container.getBean("vo2",VariousDIVO.class);
		System.out.println(vo == vo2);
	}
}
