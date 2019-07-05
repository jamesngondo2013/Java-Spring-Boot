package com.luv2code.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer>{

	// that's it ... no need to write any code LOL!
	
	// add a method to sort by last name
		public List<Customer> findAllByOrderByLastNameAsc();
		
		
		
}
