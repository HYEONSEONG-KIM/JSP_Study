package kr.or.ddit.validate.contraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import kr.or.ddit.multipart.MultipartFile;
// ConstraintValidator<어노테이션, 입력값의 타입>
public class FileMimeValidator implements ConstraintValidator<FileMime, MultipartFile>{

	private FileMime annotation;
	
	@Override
	public void initialize(FileMime constraintAnnotation) {
		this.annotation = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		
		boolean valid = value == null || value.isEmpty();
		
		if(!valid) {
			String mime = annotation.mime();
			String valueType = value.getContentType();
			valid =  valueType !=null && valueType.startsWith(mime);
		}
		
		return valid;
	}

}
