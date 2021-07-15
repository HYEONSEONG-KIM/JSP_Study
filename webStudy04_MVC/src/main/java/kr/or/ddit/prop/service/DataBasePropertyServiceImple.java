package kr.or.ddit.prop.service;

import java.util.Calendar;
import java.util.List;

import kr.or.ddit.prop.dao.DataBasePropertyDAO;
import kr.or.ddit.prop.dao.DataBasePropertyDaoImpl;
import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.vo.pagingVO;

public class DataBasePropertyServiceImple implements DataBasePropertyService{

	private DataBasePropertyDAO dao = new DataBasePropertyDaoImpl();
	@Override
	public List<DataBasePropertyVO> retriveDataBaseProperties(pagingVO<DataBasePropertyVO> pagingVO) {
		int totalRecord = dao.selectTotalRecored(pagingVO);
		
		pagingVO.setTotalRecord(totalRecord);
		
		List<DataBasePropertyVO> propList = 
				dao.selectDataBasePropertyList(pagingVO);
		pagingVO.setDataList(propList);
		// logic -> information
		Calendar cal = Calendar.getInstance();
		String pattern = "%s, %tc";
		for(DataBasePropertyVO prop : propList) {
			String infoValue = String.format(pattern, prop.getPropertyValue(), cal);
			prop.setPropertyValue(infoValue);
		}
		return propList;
	}
	
}
