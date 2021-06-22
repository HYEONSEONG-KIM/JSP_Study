package kr.or.ddit.marshalling;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {
	
	public static void main(String[] args) throws IOException {
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
