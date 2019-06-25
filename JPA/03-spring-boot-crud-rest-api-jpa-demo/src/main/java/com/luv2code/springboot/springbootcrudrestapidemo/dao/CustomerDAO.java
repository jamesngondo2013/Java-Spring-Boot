package com.luv2code.springboot.springbootcrudrestapidemo.dao;

import java.util.List;

import com.luv2code.springboot.springbootcrudrestapidemo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> findAll();

	public Customer findCustomerById(int theId);
	
	public void saveCustomer(Customer theCustomer);

	public void deleteCustomerById(int theId);
}
