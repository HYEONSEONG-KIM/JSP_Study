package kr.or.ddit.resource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ResourceLoaderDesc {
	public static void main(String[] args) {
		String cpRes = "log4j2.xml";
		String fsRes = "C:/Users/PC/Desktop/대덕 수업 자료/중간프로젝트/etc/t.jpg";
		String webRes = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
	
		ResourceLoader loader = new ClassPathXmlApplicationContext();
		Resource cpResource = loader.getResource("classpath:" + cpRes);
		Resource fsResource = loader.getResource("file:" + fsRes);
		Resource webResource = loader.getResource(webRes);
		System.out.println(cpResource);
		System.out.println(fsResource);
		System.out.println(webResource);
	}
}
