package kr.or.ddit.various;

import java.io.File;

import org.springframework.core.io.Resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class VariousDIVO {
	
	private String text;
	private int number;
	private boolean boolData;
	private char ch;
	private double dbl;
	
	private Resource file;
	
	public void init() {
		System.out.println(this.toString());
	}
	
	public void destroy() {
		System.out.println(getClass().getSimpleName() + "소멸");
	}
	
}
