package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.ddit.board.service.FreeBoardService.CountType;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.pagingVO;

public class FreeBoardDAOImplTest {
	
	private FreeBoardDAO dao = FreeBoardDAOImpl.getInstance();
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetInstance() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertBoard() {
	/*	FreeBoardVO board = dao.selectBoard(59);
		board.setBoNo(null);
		int result = dao.insertBoard(board);
		assertEquals(1, result);
		*/
	
	}

	@Test
	public void testSelectTotalRecord() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectBoardList() {
		/*pagingVO<FreeBoardVO> paging = new pagingVO<>(10,10);
		paging.setCurrentPage(1);
		int totalRecord = dao.selectTotalRecord(paging);
		System.out.println(totalRecord);
		paging.setTotalRecord(totalRecord);
		paging.setDataList(dao.selectBoardList(paging));
		
		List<FreeBoardVO> list = paging.getDataList();
		assertNotNull(list);*/
		fail("Not yet implemented");
	}

	@Test
	public void testSelectBoard() {
	/*	FreeBoardVO vo = dao.selectBoard(120);
		System.out.println(vo.getAttatchList().get(0));
		assertNotNull(vo);*/
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteBoard() {
		
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			int result = dao.deleteBoard(59, sqlSession);
			assertEquals(1, result);
			
		}
		
	}

	@Test
	public void testIncrementHit() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncrementRec() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncrementRep() {
		fail("Not yet implemented");
	}

}
