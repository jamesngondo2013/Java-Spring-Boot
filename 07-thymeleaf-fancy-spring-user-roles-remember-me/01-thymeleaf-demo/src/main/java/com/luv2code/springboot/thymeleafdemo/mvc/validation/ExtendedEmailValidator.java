package com.luv2code.springboot.thymeleafdemo.mvc.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

//@Email(message="Please provide a valid email address")
@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
@Target( {ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailConstraintValidator.class)
@Documented
public @interface ExtendedEmailValidator {

	//define default course code
	public String value() default "@";
		
		// define default error message
	 String message() default "";
	 
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
}
