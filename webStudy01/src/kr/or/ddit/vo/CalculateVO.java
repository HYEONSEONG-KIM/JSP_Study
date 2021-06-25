package kr.or.ddit.vo;

public class CalculateVO {

	private int left;
	private int right;
	private int result;
	private String operator;
	private String expression;
	
	
	
	public CalculateVO(int left, int right, String operator) {
		this.left = left;
		this.right = right;
		this.operator = operator;
	}
	
	public int calculate() {
		 int calResult = 0;
		if(this.operator.equals("+")) {
			calResult = this.left + this.right;  
		}else if(this.operator.equals("-")) {
			calResult = this.left - this.right;
		}else if(this.operator.equals("*")) {
			calResult = this.left * this.right;
		}else if(this.operator.equals("/")) {
			calResult = this.left / this.right;
		}else {
			calResult = this.left % this.right;
		}
		return calResult;
	}
	
	
	public int getLeft() {
		return left;
	}



	public int getRight() {
		return right;
	}



	public void setLeft(int left) {
		this.left = left;
	}
	
	public void setRight(int right) {
		this.right = right;
	}
	
	public int getResult() {
		
		return calculate();
	}
	
	
	public void setResult(int result) {
		this.result = result;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getExpression() {
		return String.format("%d %s %d = %d", left, operator, right, getResult());
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	
	
	

}
