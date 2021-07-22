package kr.or.ddit.ioc.sample.dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Repository;

@Repository
public class SampleDAOImpl_Mysql implements ISampleDAO {

	@Override
	public String selectById(String pk) {
		return "Mysql에서 " + pk + " 로 조회된 레코드";
	}
	@PostConstruct
	public void init() {
		System.out.println(getClass().getSimpleName() + "초기화");
	}
	@PreDestroy
	public void destroy() {
		System.out.println(getClass().getSimpleName() + "소멸");
	}
}
