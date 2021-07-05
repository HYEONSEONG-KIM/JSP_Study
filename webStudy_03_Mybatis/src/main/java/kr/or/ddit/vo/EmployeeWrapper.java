package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;

@Getter
public class EmployeeWrapper implements FancyTreeNode, Serializable{
	
	private EmployeeVO adaptee;

	public EmployeeWrapper(EmployeeVO adaptee) {
		super();
		this.adaptee = adaptee;
	}

	@Override
	public String getTitle() {
		return adaptee.getEname();
	}

	

	@Override
	public String getKey() {
		return Objects.toString(adaptee.getEmpno(), "");
	}

	@Override
	public boolean isLazy() {
		// TODO Auto-generated method stub
		return isFolder();
	}
	
	@Override
	public boolean isFolder() {
		return !adaptee.isLeaf();
	}
	
}
