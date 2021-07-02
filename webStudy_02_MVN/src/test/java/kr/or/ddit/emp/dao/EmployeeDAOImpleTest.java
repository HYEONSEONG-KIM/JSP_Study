package kr.or.ddit.emp.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.EmployeeVO;

public class EmployeeDAOImpleTest {

	EmployeeDAO empDAO; 
	Map<String, Object> pMap;

	@Before
	public void setUp() throws Exception {
		empDAO = EmployeeDAOImple.getInstance();
		pMap = new HashMap<String, Object>();
	}

	@Test
	public void testInsertEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectEmployeeList() {
		fail("Not yet implemented");
//		List<EmployeeVO> empList = empDAO.selectEmployeeList(pMap);
//		assertNotNull(empList);
////		assertEquals(1, empList.size());
////		assertEquals(false, empList.get(0).isLeaf());
////		System.out.println(empList.get(0));
	}

	@Test
	public void testSelectEmployee() {
		EmployeeVO vo = empDAO.selectEmployee(7369);
		assertNotNull(vo);
		System.out.println(vo);
	}

	@Test
	public void testUpdateEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteEmployee() {
		fail("Not yet implemented");
	}

}
