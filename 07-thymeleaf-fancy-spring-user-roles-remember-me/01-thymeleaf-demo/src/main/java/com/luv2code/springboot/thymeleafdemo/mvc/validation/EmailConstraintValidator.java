package com.luv2code.springboot.thymeleafdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailConstraintValidator implements ConstraintValidator<ExtendedEmailValidator, String>{

private String emailValue;
	
	@Override
	public void initialize(ExtendedEmailValidator theEmail) {
		emailValue = theEmail.value();
	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		boolean result;
		//check if the string input data is not null
		if(value != null) {
			 result = value.contains(emailValue);
		}
		else {
			result = true;
		}
		
		return result;
	}

}
