package kr.or.ddit.emp.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.EmployeeVO;

/**
 *	
 *	직원 관리(CRUD) Persistence Layer 
 *
 */
public interface EmployeeDAO {
	
	/**
	 * 신규 직원 등록
	 * @param employee
	 * @return > 0 : 성공
	 */
	public int insertEmployee(EmployeeVO employee);
	/**
	 * 검색 조건에 맞는 직원 목록 조회
	 * @param pMap : 검색 조건을 가진 Map
	 * @return
	 */
	public List<EmployeeVO> selectEmployeeList(Map<String, Object> pMap);
	/**
	 * 직원 상세 조회
	 * @param empno
	 * @return 조건에 맞는 직원이 없는 경우, null 반환
	 */
	public EmployeeVO selectEmployee(int empno);
	/**
	 * 직원 정보 수정
	 * @param employee
	 * @return > 0 : 성공
	 */
	public int updateEmployee(EmployeeVO employee);
	/**
	 * 직원 정보 삭제
	 * @param empno
	 * @return > 0 : 성공
	 */
	public int deleteEmployee(int empno);
}
