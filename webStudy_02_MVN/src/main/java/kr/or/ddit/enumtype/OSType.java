package kr.or.ddit.enumtype;

public enum OSType {
	WINDOW("윈도우"),
	ANDROID("안드로이드"),
	BLACKBERRY("블랙베리"),
	UNIX("유닉스"),
	LINUX("리눅스"),
	MAC("맥"),
	OTHER("기타");
	
	private String osName;

	private OSType(String osName) {
		this.osName = osName;
	}
	
	public String getOsName() {
		return this.osName;
	}
	
	public static String findUserOs(String userAgent) {
		
		OSType os = OSType.OTHER;
		
		if(userAgent != null) {
			userAgent = userAgent.toUpperCase();
			for(OSType tmp : values()) {
				if(userAgent.indexOf(tmp.name()) >= 0) {
					os = tmp;
					break;
				}
			}
		}
		
		return os.getOsName();
	}
	
}
