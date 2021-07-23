package kr.or.ddit.emp.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.EmployeeWrapper;

/**
 *	직원 관리(CRUD) Business Logic Layer 
 *
 */
public interface EmployeeService {
	/**
	 * 신규 직원 등록
	 * @param employee
	 * @return OK, FAIL
	 */
	public ServiceResult createEmployee(EmployeeVO employee);
	
	/**
	 * 검색 조건에 맞는 직원 목록 조회
	 * @param pMap
	 * @return 조건에 맞는 직원이 없는 경우, size() == 0
	 */
	public List<EmployeeWrapper> retrieveEmployeeList(Map<String, Object> pMap);
	/**
	 * 직원 상세 조회
	 * @param empno
	 * @return 해당 직원이 존재하지 않는 경우, DataNotFoundException 발생
	 */
	public EmployeeVO retrieveEmployee(int empno);
	
	/**
	 * 직원 정보 수정
	 * @param employee
	 * @return DataNotFoundException, OK, FAIL
	 */
	public ServiceResult modifyEmployee(EmployeeVO employee);
	
	/**
	 * 직원 정보 삭제
	 * @param empno
	 * @return DataNotFoundException, OK, FAIL
	 */
	public ServiceResult removeEmployee(int empno);
}
