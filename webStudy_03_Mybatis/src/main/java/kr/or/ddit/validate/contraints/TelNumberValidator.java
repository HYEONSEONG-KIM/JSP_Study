package kr.or.ddit.validate.contraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class TelNumberValidator implements ConstraintValidator<TelNumber, String> {

	private TelNumber annotation;
	
	@Override
	public void initialize(TelNumber constraintAnnotation) {
		this.annotation = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		boolean valid = value == null || value.isEmpty();
		if(!valid) {
			String regexp = annotation.regexp();
			valid = value.matches(regexp);
		}
		
		return valid;
	}

}
