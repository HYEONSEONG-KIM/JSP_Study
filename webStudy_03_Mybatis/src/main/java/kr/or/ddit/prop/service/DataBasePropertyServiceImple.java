package kr.or.ddit.prop.service;

import java.util.Calendar;
import java.util.List;

import kr.or.ddit.prop.dao.DataBasePropertyDAO;
import kr.or.ddit.prop.dao.DataBasePropertyDaoImpl;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyServiceImple implements DataBasePropertyService{

	private DataBasePropertyDAO dao = new DataBasePropertyDaoImpl();
	@Override
	public List<DataBasePropertyVO> retriveDataBaseProperties(DataBasePropertyVO param) {
		List<DataBasePropertyVO> propList = dao.selectDataBasePropertyList(param);
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
