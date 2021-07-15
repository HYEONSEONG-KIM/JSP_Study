package kr.or.ddit.emp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.commons.exception.DataNotFoundException;
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
		int result = empDAO.updateEmployee(employee);
		
		if(employee == null) {
			throw new DataNotFoundException("데이터를 찾을 수 없습니다");
		}
		
		if(result > 0) {
			return ServiceResult.OK;
		}else{
			return ServiceResult.FAIL;
		}
	}

	@Override
	public ServiceResult removeEmployee(int empno) {
		// TODO Auto-generated method stub
		return null;
	}

}
