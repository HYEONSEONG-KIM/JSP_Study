package kr.or.ddit.prop.dao;

import java.util.List;

import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.vo.pagingVO;

/**
 * 
 * DAO(Data Access Object) : 데이터 저장 계층(Persistence Layer)에 접근하는 역할 수행
 * 
 *
 */
public interface DataBasePropertyDAO {
	public int selectTotalRecored(pagingVO<DataBasePropertyVO> pagingVO);
	public List<DataBasePropertyVO> selectDataBasePropertyList(pagingVO<DataBasePropertyVO> pagingVO);
}
