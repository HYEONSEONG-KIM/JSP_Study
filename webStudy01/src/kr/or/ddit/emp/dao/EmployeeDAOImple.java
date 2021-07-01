package kr.or.ddit.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.EmployeeWrapper;

public class EmployeeDAOImple implements EmployeeDAO {

	private static EmployeeDAOImple self;
	
	private EmployeeDAOImple() {
		super();
	}
	
	public static EmployeeDAOImple getInstance() {
		if(self == null) {
			self = new EmployeeDAOImple();
		}
		return self;
	}

	@Override
	public int insertEmployee(EmployeeVO employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EmployeeVO> selectEmployeeList(Map<String, Object> pMap) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT                          ");
	      sql.append("    empno, ename, job,          ");
	      sql.append("    mgr,  hiredate,sal,         ");
	      sql.append("    comm, a.deptno, dname       ");
	      sql.append("    ,(select decode(count(*), 0, '1', '0') from emp c where c.mgr = a.empno) AS LEAF      ");
	      sql.append("FROM                            ");
	      sql.append("    emp a left outer join dept b");
	      sql.append("    on(a.deptno = b.deptno)     ");
	      if(pMap != null && pMap.containsKey("mgr")) {
	    	  sql.append("WHERE mgr = ?               ");
	      }else {
	    	  sql.append("WHERE mgr IS NULL               ");
	      }
	      
	      try(
	    	Connection conn = ConnectionFactory.getConnection();
	    	PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	    ){	
	    	  if(pMap != null && pMap.containsKey("mgr")) {
	    		pstmt.setInt(1, (Integer)pMap.get("mgr"));  
	    	  }
	    	  ResultSet rs = pstmt.executeQuery();
	    	  
	    	  List<EmployeeVO> empList = new ArrayList<EmployeeVO>();
	    	  while(rs.next()) {
	    		  EmployeeVO empVO =EmployeeVO.builder()
	    				  .empno(rs.getInt("EMPNO"))
	    				  .ename(rs.getString("ENAME"))
	    				  .job(rs.getString("JOB"))
	    				  .mgr(rs.getInt("MGR"))
	    				  .hiredate(rs.getString("HIREDATE"))
	    				  .sal(rs.getInt("SAL"))
	    				  .comm(rs.getInt("COMM"))
	    				  .deptno(rs.getInt("DEPTNO"))
	    				  .dname(rs.getString("DNAME"))
	    				  .leaf(rs.getBoolean("LEAF"))
	    				  .build();
	    		  empList.add(empVO);
	    		  
	    	  }
	    	  rs.close();
	    	  return empList;
	      }catch(SQLException e) {
	    	  throw new RuntimeException(e);
	      }
		
		
	}

	@Override
	public EmployeeVO selectEmployee(int empno) {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT                          ");
	      sql.append("    empno, ename, job,          ");
	      sql.append("    mgr,  hiredate,sal,         ");
	      sql.append("    comm, a.deptno, dname       ");
	      sql.append("FROM                            ");
	      sql.append("    emp a left outer join dept b");
	      sql.append("    on(a.deptno = b.deptno)     ");
	      sql.append("WHERE empno = ?               ");
		
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setInt(1, empno);
			ResultSet rs = pstmt.executeQuery();
			EmployeeVO empVO = null;
			if(rs.next()) {
				
				empVO = EmployeeVO.builder()
	    				  .empno(rs.getInt("EMPNO"))
	    				  .ename(rs.getString("ENAME"))
	    				  .job(rs.getString("JOB"))
	    				  .mgr(rs.getInt("MGR"))
	    				  .hiredate(rs.getString("HIREDATE"))
	    				  .sal(rs.getInt("SAL"))
	    				  .comm(rs.getInt("COMM"))
	    				  .deptno(rs.getInt("DEPTNO"))
	    				  .dname(rs.getString("DNAME"))
	    				  .build();

			}

			return empVO;
		}catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public int updateEmployee(EmployeeVO employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployee(int empno) {
		// TODO Auto-generated method stub
		return 0;
	}

}
