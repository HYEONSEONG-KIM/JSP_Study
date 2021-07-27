package kr.or.ddit.member.service;

import static org.junit.Assert.*;

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

import kr.or.ddit.commons.UserNotFoundExcpetion;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.MemberVO;

@RunWith(SpringRunner.class)
@ContextHierarchy({
	@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml"),
	@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
})
@WebAppConfiguration
@Transactional
public class MemberServiceImplTest {

	@Inject
	private MemberService service;
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreatMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveMemberList() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyMember() {
		fail("Not yet implemented");
	}

	@Test(expected=UserNotFoundExcpetion.class)
	public void testRemoveMemberException() {
		MemberVO member = MemberVO.builder()
							.memId("asdasd")
							.build();
		service.removeMember(member);
	}
	 
	@Test
	public void testRemoveMember() {
		MemberVO member = MemberVO.builder()
							.memId("a001")
							.memPass("123123")
							.build();
		ServiceResult result = service.removeMember(member);
		assertEquals(ServiceResult.INVALIDPASSWORD, result);
		
		member.setMemPass("asdfasdf");
		result = service.removeMember(member);
		assertEquals(ServiceResult.OK, result);
	}

}
