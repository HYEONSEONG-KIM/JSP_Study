package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * 직원관리 Domain Layer
 *
 */
//@Getter
//@Setter
@EqualsAndHashCode(of = "empno")
/*@ToString(exclude = "test")*/
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Builder
@Data
public class EmployeeVO implements Serializable{

	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private String hiredate;
	private Integer sal;
	private Integer comm;
	private Integer deptno;
	private String dname;
	private boolean leaf;
	
	

}
