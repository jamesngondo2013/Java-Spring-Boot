package com.luv2code.springboot.springbootcrudrestapidemo.service;

import java.util.List;

import com.luv2code.springboot.springbootcrudrestapidemo.entity.Customer;

public interface CustomerService {

	public List<Customer> findAll();

	public void saveCustomer(Customer theCustomer);

	public Customer findCustomerById(int theId);

	public void deleteCustomerById(int theId);
	
}
