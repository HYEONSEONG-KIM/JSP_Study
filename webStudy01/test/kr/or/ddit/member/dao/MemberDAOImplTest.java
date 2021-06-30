package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testSelectMemberById() {
		MemberDAO dao = new MemberDAOImpl();
		String mem_id = "a001";
		MemberVO vo = dao.selectMemberById(mem_id);
		assertNotNull(dao);
	}

}
