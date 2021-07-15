package kr.or.ddit.validate;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

public class MemberVOValidateTest {


	private static Validator validator;
	private static final Logger logger = LoggerFactory.getLogger(MemberVOValidateTest.class);
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		
		
	}



//	@Test
	public void test() {
		MemberVO target = MemberVO.builder().build();
		
		Set<ConstraintViolation<MemberVO>> violations =  validator.validate(target);
		for( ConstraintViolation<MemberVO> single : violations) {
			String key = single.getPropertyPath().toString();
			String message = single.getMessage();
			logger.info("{} : {}", key, message);
			
		}
	}
	
	@Test
	public void testValidatorUtils() {
		MemberVO target = MemberVO.builder()
						/*	.memId("a001")
							.memPass("ab")
							.memMail("asdasd@asd")*/
							.memHp("123-123-1231")
							.memComtel("123-123-1232")
							.memHometel("12312")
							.build();
		ValidatorUtils<MemberVO> utils = new ValidatorUtils<>();
		Map<String, List<String>> errors = new HashMap<String, List<String>>();
		boolean valid = utils.validate(target, errors, InsertGroup.class);
		
		if(!valid) {
			for(Entry<String, List<String>> entry : errors.entrySet()) {
				logger.info("{} : {} " , entry.getKey(), entry.getValue());
			}
		}
	}

}













