package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.ProdVO;

public class ProdDAOImplTest {
	
	ProdDAO dao = ProdDAOImpl.getInstance();
	// xml 설정에서 info등급만 log찍힘
	// 타입 안정성을 위해 클래스를 넣음(해당 클래스의 경로인 kr.or.ddit가 설정)
	Logger logger = LoggerFactory.getLogger(ProdDAOImplTest.class);
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSelectProd() {
		String id = "P101000001";
		
		ProdVO prodVO = dao.selectProd(id);
		assertNotNull(prodVO);
		if(logger.isInfoEnabled()) {
			logger.info("Prod : {}", prodVO);
			
		}
		
		assertEquals(2, prodVO.getMemberList().size());
		/*if(logger.isDebugEnabled()) {
			logger.info("memberList : {}", prodVO.getMemberList());
			
		}*/
		assertNotNull(prodVO.getBuyer());
		
		if(logger.isInfoEnabled()) {
			logger.info("Prod : {}", prodVO.getBuyer());
			
		}
		
		
		
		
	}

}
