package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.pagingVO;

public class ProdDAOImplTest {
	
//	ProdDAO dao = ProdDAOImpl.getInstance();
	// xml 설정에서 info등급만 log찍힘
	// 타입 안정성을 위해 클래스를 넣음(해당 클래스의 경로인 kr.or.ddit가 설정)
	ProdDAO dao =  new ProdDAOImpl();
	Logger logger = LoggerFactory.getLogger(ProdDAOImplTest.class);
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSelectProd() {
	/*	String id = "P101000001";
		
		ProdVO prodVO = dao.selectProd(id);
		assertNotNull(prodVO);
		if(logger.isInfoEnabled()) {
			logger.info("Prod : {}", prodVO);
			
		}
		
		assertEquals(2, prodVO.getMemberList().size());
		if(logger.isDebugEnabled()) {
			logger.info("memberList : {}", prodVO.getMemberList());
			
		}
		assertNotNull(prodVO.getBuyer());
		
		if(logger.isInfoEnabled()) {
			logger.info("Prod : {}", prodVO.getBuyer());
			
		}
		*/
	/*	pagingVO<ProdVO> pagingVO = new pagingVO<>(5,5);
		ProdVO prod = new ProdVO();
		prod.setProdBuyer("P30201");
		prod.setProdLgu("P101");
		prod.setProdBuyer("P30201");
		int total = dao.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(total);
		pagingVO.setCurrentPage(2);
		pagingVO.setDetailSearch(prod);

		pagingVO.setDataList(dao.selectProdList(pagingVO));
		assertNotNull(pagingVO.getDataList());
		*/
		
		
		ProdVO VO = dao.selectProd("P101000001");
		VO.setProdName("모니터 삼성전자20인치칼라");
		int result = dao.updateProd(VO);
		/*insertVO.setProdId(null);
		
		int result = dao.insertProd(insertVO);*/
		assertEquals(1, result);
		
		
	}

}











