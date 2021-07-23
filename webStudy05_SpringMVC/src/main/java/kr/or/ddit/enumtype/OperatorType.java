package kr.or.ddit.enumtype;

public enum OperatorType {
	PLUS('+', new RealOperator() {
		public double operate(double left, double right) {
			return left + right;
		}
	}),
	MINUS('-',(left, right)->{ return left - right; }),
	MULTIPLY('*',(left, right)->{ return left * right; }),
	DIVIDE('/', (left, right)->{ return left / right; });
	
	@FunctionalInterface
	public static interface RealOperator{
		// 추상 메서드가 하나인것 = > functional interface
		public double operate(double left, double right);
	}
	
	private char sign;
	private RealOperator realOperator;

	private OperatorType(char sign, RealOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	
	public double operate(double left, double right) {
		return realOperator.operate(left, right);
	}
	
	public char getSign() {
		return sign;
	}
	
	private static final String EXPPTRN = "%f %c %f = %f";
	
	public String getExpression(double left, double right) {
		return String.format(EXPPTRN, left, sign, right, operate(left, right));
		
	}
	
	

}











