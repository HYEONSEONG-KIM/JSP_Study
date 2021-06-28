package kr.or.ddit.designpattern.adapter;

public class Adaptee {
	
	public void specificRequest() {
		System.out.println(getClass().getSimpleName() +" 에서 구체적인 요청을 처리");
	}
}
