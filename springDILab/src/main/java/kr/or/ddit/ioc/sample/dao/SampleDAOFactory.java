package kr.or.ddit.ioc.sample.dao;

public class SampleDAOFactory {
	
	public ISampleDAO getSampleDAO() {
		return new SampleDAOImpe_oracle();
	}
}
