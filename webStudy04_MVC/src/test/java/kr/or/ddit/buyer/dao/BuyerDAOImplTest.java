package kr.or.ddit.buyer.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.pagingVO;

public class BuyerDAOImplTest {
	
	BuyerDAO dao = BuyerDAOImpl.getInstance();
	pagingVO<BuyerVO> paging = new pagingVO<>(3, 3);
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetInstance() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuyerTotalRecord() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testSelectBuyerList() {
		int result = dao.buyerTotalRecord(paging);
		paging.setCurrentPage(1);
		BuyerVO detailSearch = new BuyerVO();
		detailSearch.setBuyerName("전자");
		
		paging.setDetailSearch(detailSearch);
		
		paging.setTotalRecord(result);
		List<BuyerVO> list = dao.selectBuyerList(paging);
		assertNotNull(list);
	}

	@Test
	public void testSelectBuyer() {
		BuyerVO vo = new BuyerVO();
		vo = dao.selectBuyer("P10101");
		System.out.println(vo.getLprod().getLprodNm());
		assertNotNull(vo);
	}

	@Test
	public void testInsertBuyer() {
		BuyerVO vo = new BuyerVO();
		vo = dao.selectBuyer("P10101");
		vo.setBuyerId(null);
		
		int result = dao.insertBuyer(vo);
		assertEquals(1, result);
	}

	@Test
	public void testUpdateBuyer() {
		fail("Not yet implemented");
	}

}
