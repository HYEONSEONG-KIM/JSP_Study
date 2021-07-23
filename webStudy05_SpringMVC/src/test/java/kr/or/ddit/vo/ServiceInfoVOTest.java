package kr.or.ddit.vo;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

public class ServiceInfoVOTest {

	@Test
	public void test() throws JAXBException {
	/*	MenuVO menu1 = new MenuVO();
		menu1.setCode("STANDARD");
		menu1.setLink("/02/standard.jsp");
		
		MenuVO menu2 = new MenuVO();
		menu2.setCode("STANDARD");
		menu2.setLink("/02/standard.jsp");
		
		List<MenuVO> menuList = new ArrayList<>();
		menuList.add(menu1);
		menuList.add(menu2);
		
		ServiceInfoVO vo = new ServiceInfoVO();
		vo.setMenuList(menuList);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(ServiceInfoVO.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(vo, System.out);
	*/
		
		
		InputStream is = getClass().getResourceAsStream("../serviceInfo.xml");
			
		JAXBContext jaxbContext = JAXBContext.newInstance(ServiceInfoVO.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		ServiceInfoVO infoVO =  (ServiceInfoVO) unmarshaller.unmarshal(is);
		List<MenuVO> vo = infoVO.getMenuList();
		System.out.println(vo);
			
	}

}










