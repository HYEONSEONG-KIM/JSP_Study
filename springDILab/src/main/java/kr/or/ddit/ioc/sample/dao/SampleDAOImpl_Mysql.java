package kr.or.ddit.ioc.sample.dao;

public class SampleDAOImpl_Mysql implements ISampleDAO {

	@Override
	public String selectById(String pk) {
		return "Mysql에서 " + pk + " 로 조회된 레코드";
	}

	public void init() {
		System.out.println(getClass().getSimpleName() + "초기화");
	}
	
	public void destroy() {
		System.out.println(getClass().getSimpleName() + "소멸");
	}
}
