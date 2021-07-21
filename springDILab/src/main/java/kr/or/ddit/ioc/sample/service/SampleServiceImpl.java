package kr.or.ddit.ioc.sample.service;

import java.util.Date;

import kr.or.ddit.ioc.sample.dao.ISampleDAO;
import kr.or.ddit.ioc.sample.dao.SampleDAOFactory;
import kr.or.ddit.ioc.sample.dao.SampleDAOImpe_oracle;

public class SampleServiceImpl implements ISampleService {

	// 1. new 키워드로 의존 객체 직접 생성 -> 결합력 최상(망!!)
//	private ISampleDAO dao = new SampleDAOImpe_oracle();
	
	// 2. Factory Object Pattern 활용 -> 결합력 잔존
//	private ISampleDAO dao = new SampleDAOFactory().getSampleDAO();
	
	// 3. Strategy Pattern(DI의 핵심!!) 전략패턴의 활용
	//	setter injection
	//	coustrutor injection
	private ISampleDAO dao;
	
	public SampleServiceImpl() {}
	
	public SampleServiceImpl(ISampleDAO dao) {
		super();
		this.dao = dao;
	}

	public void setDao(ISampleDAO dao) {
		this.dao = dao;
	}
	
	public void init() {
		System.out.println(getClass().getSimpleName() + "초기화, injected dao : " + dao);
	}
	
	public void destroy() {
		System.out.println(getClass().getSimpleName() + "소멸");
	}
	
	@Override
	public StringBuffer retrieveById(String pk) {
		String rawData = dao.selectById(pk);
		StringBuffer content = new StringBuffer();
		content.append(rawData);
		content.append("\n" + new Date() + " 에 가공된 컨텐츠");
		return content;
	}
	
	

}
