package kr.or.ddit.designpattern.templatemethod;

public class ConcreteClass2 extends TemplateClass {

	@Override
	protected void secondStep() {
		System.out.println("두번째 단계 - 또 다른 작업 수행");
	}

}
