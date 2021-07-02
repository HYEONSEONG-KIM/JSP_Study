package kr.or.ddit.emp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.emp.dao.EmployeeDAO;
import kr.or.ddit.emp.dao.EmployeeDAOImple;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.EmployeeWrapper;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeServiceImpl() {}
	private static EmployeeServiceImpl self;
	
	public static EmployeeServiceImpl getInstance() {
		if(self == null) {
			self = new EmployeeServiceImpl();
		}
		return self;
	}
	
	private EmployeeDAO empDAO = EmployeeDAOImple.getInstance();
	
	@Override
	public ServiceResult createEmployee(EmployeeVO employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeWrapper> retrieveEmployeeList(Map<String, Object> pMap) {
		List<EmployeeVO> empList = empDAO.selectEmployeeList(pMap);
		List<EmployeeWrapper> wrapperList = new ArrayList<>();
		empList.forEach((employee)->{wrapperList.add(new EmployeeWrapper(employee));});
		return wrapperList;
	}

	@Override
	public EmployeeVO retrieveEmployee(int empno) {
		EmployeeVO empVO = empDAO.selectEmployee(empno);
		return empVO;
	}

	@Override
	public ServiceResult modifyEmployee(EmployeeVO employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeEmployee(int empno) {
		// TODO Auto-generated method stub
		return null;
	}

}
