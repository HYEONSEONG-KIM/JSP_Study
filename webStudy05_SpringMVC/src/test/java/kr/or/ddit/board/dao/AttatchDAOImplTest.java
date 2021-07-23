package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AttatchVO;
	

public class AttatchDAOImplTest {
	
	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	private AttatchDAO dao = AttatchDAOImpl.getInstance();
	
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
	public void testInsertAttatchs() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectAttatch() {
		try(
			SqlSession sqlSession =sqlSessionFactory.openSession();
		){
			AttatchVO vo = dao.selectAttatch(1025, sqlSession);
			assertNotNull(vo);
			System.out.println(vo);
		}
	}

	@Test
	public void testDeleteAttatchs() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncrementDownCount() {
		fail("Not yet implemented");
	}

}
