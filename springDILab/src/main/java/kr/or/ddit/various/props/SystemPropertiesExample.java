package kr.or.ddit.various.props;

import java.util.Map;
import java.util.Properties;

public class SystemPropertiesExample {
	public static void main(String[] args) {
		Map<String, String> envs = System.getenv();
		envs.forEach((name, value)->{
			System.out.printf("%s : %s\n", name, value);
			
		});
		Properties props = System.getProperties();
		props.forEach((name,value)->{
			System.err.printf("%s : %s\n", name, value);
			
		});
	}
}
