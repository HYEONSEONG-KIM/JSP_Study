package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

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
		assertNotNull(vo);*/
//		System.out.println(vo);
	/*	List<MemberVO> list = dao.selectMemberList();
		assertNotNull(list);*/
		/*MemberVO vo = dao.selectMemberDatail("kimgueee");
		System.out.println(vo);
		
		
		vo.setMemName("test");
		
		int result = dao.updateMember(vo);
		assertEquals(1, result);
		System.out.println(result);*/
		int rowcnt = dao.deleteMember("a001");
		assertEquals(1, rowcnt);
	}

}
