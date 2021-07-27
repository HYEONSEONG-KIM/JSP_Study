package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.TestWebAppConfiguration;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ZipVO;

@RunWith(SpringRunner.class)

@TestWebAppConfiguration
public class MemberDAOImplTest {
	@Inject
	private MemberDAO dao;
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
	
		MemberVO vo = dao.selectMemberById("a001");
		System.out.println(vo);
		//assertNotNull(vo);
//		System.out.println(vo);
	/*	List<MemberVO> list = dao.selectMemberList();
		assertNotNull(list);*/
		/*MemberVO vo = dao.selectMemberDatail("a001");
		vo.setMemId("a007");
		vo.setMemDelete(null);
		
		int result = dao.insertMember(vo);
		
		System.out.println(result);*/
		
		/*vo.setMemName("test");
		
		int result = dao.updateMember(vo);
		assertEquals(1, result);
		System.out.println(result);
		int rowcnt = dao.deleteMember("a001");
		assertEquals(1, rowcnt);*/
//	
//		List<ZipVO> list = dao.selectZip();
//		assertNotNull(list);
	}

}
