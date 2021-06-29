package kr.or.ddit.prop.service;

import java.util.List;

/**
 * 	Business Logic Layer : raw 데이터 가공 logic을 운영하기 위한 계층
 * 
 * 
 */
import kr.or.ddit.vo.DataBasePropertyVO;

public interface DataBasePropertyService {
	public List<DataBasePropertyVO> retriveDataBaseProperties();
}
