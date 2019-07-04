package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.luv2code.springboot.thymeleafdemo.entity.Customer;

public interface CustomerService {

	public List<Customer> findAll();

	public void saveCustomer(Customer theCustomer);

	public Customer findCustomerById(int theId);

	public void deleteCustomerById(int theId);
	
	 public Page<Customer> findPaginated(Pageable pageable);
	
}
