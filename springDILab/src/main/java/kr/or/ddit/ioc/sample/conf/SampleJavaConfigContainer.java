package kr.or.ddit.ioc.sample.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.ioc.sample.dao.ISampleDAO;
import kr.or.ddit.ioc.sample.dao.SampleDAOImpl_Mysql;
import kr.or.ddit.ioc.sample.service.ISampleService;
import kr.or.ddit.ioc.sample.service.SampleServiceImpl;

@Configuration
@ComponentScan(basePackages="kr.or.ddit.ioc.sample")
public class SampleJavaConfigContainer {
	@Scope("prototype")
	public ISampleDAO getSampleDAO() {
		return new SampleDAOImpl_Mysql();
	}

	//@Bean
	public ISampleService sampleService1() {
		return new SampleServiceImpl(getSampleDAO()); 
	}

	//@Bean("service2")
	public ISampleService sampleService2(ISampleDAO dao) {
		SampleServiceImpl service = new SampleServiceImpl();
		service.setDao(dao);
		return service;
	}
}















