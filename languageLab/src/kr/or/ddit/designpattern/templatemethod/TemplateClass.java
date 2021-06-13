package kr.or.ddit.designpattern.templatemethod;


public abstract class TemplateClass {
	// final => 오버라이드 불가-> 순서가 변하지 않음
	public final void template() { // 해당 메서드가 서블릿에서의 service
		firstStep();
		secondStep();
		thirdStep();
	}
	
	private void firstStep() {
		System.out.println("첫번째 단계");
	}
	// 세컨드가 구현되지 않았기에 인스턴스 생성 불가
	// doxxx 계열의 메서드역할
	protected abstract void secondStep();
	
	private void thirdStep() {
		System.out.println("세번째 단계");
	}
}
