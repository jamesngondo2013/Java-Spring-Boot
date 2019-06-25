package com.luv2code.springboot.springbootcrudrestapidemo.exception;

public class CustomerNotFoundException extends RuntimeException{

	public CustomerNotFoundException(String message) {
		super(message);
		
	}
	
}
