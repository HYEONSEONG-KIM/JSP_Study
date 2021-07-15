package kr.or.ddit;

import static org.junit.Assert.*;

import java.util.ResourceBundle;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResourceBundleTest {

	@Test
	public void test() throws JsonProcessingException {
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.servlet05.message");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(bundle);
		System.out.println(json);
		
	}

}
