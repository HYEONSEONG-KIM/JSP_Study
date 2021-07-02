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
		MemberVO vo = dao.selectMemberDatail("a001");
		assertNotNull(vo);
		
	}

}
