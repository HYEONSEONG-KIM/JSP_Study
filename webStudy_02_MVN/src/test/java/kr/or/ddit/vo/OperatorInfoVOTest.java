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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OperatorInfoVOTest {

	@Test
	public void test() throws JAXBException, JsonProcessingException {
		
		/*OperatorVO vo = new OperatorVO();
		vo.setValue("+");
		
		OperatorVO vo2 = new OperatorVO();
		vo2.setValue("-");
		
		OperatorInfoVO opList = new OperatorInfoVO();
		List<OperatorVO> list = new ArrayList<OperatorVO>();
		list.add(vo);
		list.add(vo2);
		opList.setOperatorList(list);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(OperatorInfoVO.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(opList, System.out);*/
		
		
			InputStream is = getClass().getResourceAsStream("../operator.xml");
			
			JAXBContext jaxbContext = JAXBContext.newInstance(OperatorInfoVO.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			OperatorInfoVO infoVO = (OperatorInfoVO) unmarshaller.unmarshal(is);
			List<OperatorVO> li = infoVO.getOperatorList();
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(li);
			System.out.println(json);
		
		
	}

}




