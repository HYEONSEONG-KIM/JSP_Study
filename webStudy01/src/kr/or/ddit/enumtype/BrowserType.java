package kr.or.ddit.enumtype;

public enum BrowserType{
	MISE("익스플러로 구버전"), 
	TRIDENT("익스플로러 최신버전"), 
	OPERA("오페라"), 
	FIREFOX("파이어폭스"), 
	EDG("엣지"), 
	CHROME("크롬"), 
	SAFARI("사파리"), 
	OTHER("기타");
	private String browserName;
	
	private BrowserType(String browerName){
		this.browserName = browerName;
	}
	public String getBrowserName(){
		return this.browserName;
	}

	public static String paeseUserAgent(String userAgent) {
		BrowserType finded = BrowserType.OTHER;

		if(userAgent != null) {
			userAgent = userAgent.toUpperCase();
			for(BrowserType tmp : values()){
				if(userAgent.indexOf(tmp.name()) >= 0){
					finded = tmp;
					break;
				}
		}
		}
		return finded.getBrowserName();
	}
}