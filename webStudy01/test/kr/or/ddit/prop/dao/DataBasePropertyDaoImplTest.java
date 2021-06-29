package kr.or.ddit.prop.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prop.service.DataBasePropertyService;
import kr.or.ddit.prop.service.DataBasePropertyServiceImple;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDaoImplTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testSelectDataBasePropertyList() throws JsonProcessingException {
		DataBasePropertyDAO dao = new DataBasePropertyDaoImpl();
		List<DataBasePropertyVO> propList = dao.selectDataBasePropertyList();

		assertNotNull(propList);
	}

}
