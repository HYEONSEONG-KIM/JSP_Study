package kr.or.ddit.various.props;

import kr.or.ddit.various.VariousDIVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Getter
@ToString
@Slf4j
public class DataBaseInfoPropVO {
	private String driverClassName;
	private String url;
	private String user;
	private String password;

	private int initialSize;
	private int maxWait;
	
	private VariousDIVO vdivo;
	
	public void init() {
		log.info("주입 상태 : {}", this);
	}
}
