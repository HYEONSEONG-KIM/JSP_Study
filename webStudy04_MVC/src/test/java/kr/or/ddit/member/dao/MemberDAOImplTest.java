package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ZipVO;

public class MemberDAOImplTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		MemberDAO dao = MemberDAOImpl.getInstacne();
		/*MemberVO vo = dao.selectMemberById("a001");
		System.out.println(vo);*/
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
	
		List<ZipVO> list = dao.selectZip();
		assertNotNull(list);
	}

}
