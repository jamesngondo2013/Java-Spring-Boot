package com.luv2code.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	// that's it ... no need to write any code LOL!
	
	// add a method to sort by last name
		public List<Customer> findAllByOrderByLastNameAsc();
		
}
