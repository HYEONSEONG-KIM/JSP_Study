package kr.or.ddit.designpattern;

import java.util.Arrays;
import java.util.List;

import kr.or.ddit.designpattern.templatemethod.ConcreteClass1;
import kr.or.ddit.designpattern.templatemethod.ConcreteClass2;
import kr.or.ddit.designpattern.templatemethod.TemplateClass;

public class TemplateMethodPatternTest {
	
	public static void main(String[] args) {
		// T...a ->가변 파라미터(파라미터가 넘어가도 안넘어가도 댐)
		List<TemplateClass> list = 
				Arrays.asList(new ConcreteClass1(), new ConcreteClass2());
		
		for(TemplateClass tmp: list) {
			tmp.template();
		}
	}
}
