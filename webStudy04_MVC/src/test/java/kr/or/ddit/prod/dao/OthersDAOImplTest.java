package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.BuyerVO;

public class OthersDAOImplTest {

	OthersDAO dao = new OthersDAOImpl();
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		List<Map<String, Object>> list = dao.selectLprodList();
		assertNotNull(list);
		
		List<BuyerVO> list2 = dao.selectBuyerList();
		assertNotNull(list2);
	}

}
