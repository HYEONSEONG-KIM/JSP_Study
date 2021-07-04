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
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateEmployee() {
		EmployeeVO vo = new EmployeeVO();
		vo.setEmpno(7839);
		vo.setEname("KING");
		vo.setJob("PRESIDENT");
		vo.setMgr(0);
		vo.setHiredate("1981-11-17 00:00:00");
		vo.setSal(6000);
		vo.setComm(0);
		vo.setDeptno(10);
		
		System.out.println(empDAO.updateEmployee(vo));
		
	}

	@Test
	public void testDeleteEmployee() {
		fail("Not yet implemented");
	}

}
