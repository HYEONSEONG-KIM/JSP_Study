package kr.or.ddit.enumtype;

public enum BtsType {
	BUI("bui"),
	JHOP("jhop"),
	JIMIN("jimin"),
	JIN("jin"),
	JUNGKUK("jungkuk"),
	RM("rm"),
	SUGA("suga");
	
	private String memName;
	
	private BtsType(String memName) {
		this.memName = memName;
	}
	
	public String getMemName() {
		return this.memName;
	}
	
	public static String findMember(String memName) {
		BtsType finded = null;

		if(memName != null) {
			memName = memName.toUpperCase();
			for(BtsType tmp : values()){
				if(memName.indexOf(tmp.name()) >= 0){
					finded = tmp;
					break;
				}
		}
		}
		return finded.getMemName();
	}
}
