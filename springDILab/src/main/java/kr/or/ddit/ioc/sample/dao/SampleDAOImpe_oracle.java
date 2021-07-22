package kr.or.ddit.ioc.sample.dao;

import org.springframework.stereotype.Repository;

@Repository("daoOracle")
public class SampleDAOImpe_oracle implements ISampleDAO {

	@Override
	public String selectById(String pk) {
		// TODO Auto-generated method stub
		return "Oracle 에서 가져온 " + pk + " 에 해당하는 레코드";
	}

	public void init() {
		System.out.println(getClass().getSimpleName() + "초기화");
	}
	
	public void destroy() {
		System.out.println(getClass().getSimpleName() + "소멸");
	}
}
