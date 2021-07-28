package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.TestWebAppConfiguration;
import kr.or.ddit.vo.FreeReplyVO;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@TestWebAppConfiguration
@Slf4j
public class FreeReplyDAOTest {

	@Inject
	private WebApplicationContext container;
	@Inject
	private FreeReplyDAO dao;
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBoardReplyList() {
		List<FreeReplyVO> replyList = dao.boardReplyList(60);
		assertNotNull(replyList);
	}

	@Test
	public void testInsertBoardReply() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBoardReply() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteBoardReply() {
		fail("Not yet implemented");
	}

}
