package kr.or.ddit.designpattern.adapter;

public class OtherConcrete implements Target {

	@Override
	public void request() {
		System.out.println(getClass().getSimpleName() + "에서 요청 처리 했음");
	}

}
