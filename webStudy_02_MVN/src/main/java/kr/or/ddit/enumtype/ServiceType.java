package kr.or.ddit.enumtype;

public enum ServiceType {
	STANDARD("/02/standard.jsp"),
	CALENDAR("/04/calendarTea.jsp"),
	INDEX("/WEB-INF/views/index.jsp"),
	OTHER("");
	
	private String path;
	
	private ServiceType(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}
	
	
	public static String pathName(String service) {
		ServiceType type = ServiceType.OTHER;
		if(service != null) {
			service = service.toUpperCase();
			for(ServiceType tmp : values()) {
				if(service.indexOf(tmp.name()) >= 0) {
					type = tmp;
				}
			}
		}
		return type.getPath();
		
	}
	
	
	
}
