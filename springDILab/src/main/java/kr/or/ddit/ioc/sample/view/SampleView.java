package kr.or.ddit.ioc.sample.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.ioc.sample.dao.ISampleDAO;
import kr.or.ddit.ioc.sample.dao.SampleDAOImpe_oracle;
import kr.or.ddit.ioc.sample.dao.SampleDAOImpl_Mysql;
import kr.or.ddit.ioc.sample.service.ISampleService;
import kr.or.ddit.ioc.sample.service.SampleServiceImpl;

public class SampleView {

	public static void main(String[] args) {
		/*ISampleDAO dao = new SampleDAOImpl_Mysql();
		ISampleService service= new SampleServiceImpl(dao);*/
		
		ApplicationContext container = 
				new ClassPathXmlApplicationContext("kr/or/ddit/ioc/sample/conf/Sample-Context.xml");
		ISampleService service =  container.getBean(SampleServiceImpl.class);
		String pk = "a001";
		StringBuffer result = service.retrieveById(pk);
		System.out.println(result);
	}
}
