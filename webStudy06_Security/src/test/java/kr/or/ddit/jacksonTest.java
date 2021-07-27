package kr.or.ddit;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jacksonTest {

	@Test
	public void test() throws JsonProcessingException {
		Map<String, Object> target = new HashMap<String, Object>();
		target.put("prop1", "텍스트");
		target.put("prop2", 34);
		target.put("prop3", true);
		target.put("prop4", null);
		target.put("prop5", new String[] {"value1", "value2"});
		target.put("prop6", Collections.singletonMap("innerProp", "내부맵데이터"));
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(target);
		
		System.out.println(json);
		
		Map<String, Object> destMap =  mapper.readValue(json, Map.class);
		System.out.println(destMap);
	}

}
