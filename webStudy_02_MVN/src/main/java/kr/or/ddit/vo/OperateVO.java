package kr.or.ddit.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import kr.or.ddit.enumtype.OperatorType;

@JsonIgnoreProperties("mime")
public class OperateVO {

	public OperateVO() {
		super();
	}

	public OperateVO(double left, double right, OperatorType operator) {
		super();
		this.left = left;
		this.right = right;
		this.operator = operator;
	}

	private double left;
	private double right;
	private OperatorType operator;
	
	
	private double result;
	
	private String expression;
	
	public double getResult() {
		return operator.operate(left, right);
	}
	
	public String getExpression() {
		return operator.getExpression(left, right);
	}

	public double getLeft() {
		return left;
	}

	public void setLeft(double left) {
		this.left = left;
	}

	public double getRight() {
		return right;
	}

	public void setRight(double right) {
		this.right = right;
	}

	public OperatorType getOperator() {
		return operator;
	}

	public void setOperator(OperatorType operator) {
		this.operator = operator;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	
}
