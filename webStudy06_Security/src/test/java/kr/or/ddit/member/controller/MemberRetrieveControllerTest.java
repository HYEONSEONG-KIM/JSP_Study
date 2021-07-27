package kr.or.ddit.member.controller;

import static org.junit.Assert.*;
// Request가상으로 만들기 위해 import
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// status속성 사용
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
// print사용
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import javax.inject.Inject;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.TestWebAppConfiguration;

@RunWith(SpringRunner.class)
@TestWebAppConfiguration
public class MemberRetrieveControllerTest {

	@Inject
	private WebApplicationContext container;
	
	private MockMvc mockMvc; 
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(container).build();
	}

	@Test
	public void testList() throws Exception {
		// 목객체를 통해 가상의 req객체 생성 -> method와 uri주소, 파라미터 설정 -> 상태코드 -> scope에 담긴 객체 -> viewname
		mockMvc.perform(get("/member/memberList.do").param("page", "2"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("pagingVO"))
				.andExpect(view().name("member/memberList"))
				.andDo(log())
				.andReturn();
	}

	@Test
	public void testView() throws Exception {
		mockMvc.perform(get("/member/memberView.do").param("who", "a001"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("member"))
				.andExpect(view().name("/member/memberView"))
				.andDo(log())
				.andReturn();
	}

}










