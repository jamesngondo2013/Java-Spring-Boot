package com.luv2code.springboot.springbootcrudrestapidemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.springbootcrudrestapidemo.dao.CustomerDAO;
import com.luv2code.springboot.springbootcrudrestapidemo.entity.Customer;

@Repository
public class CustomerServiveJPAImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	public List<Customer> findAll() {
		
		return customerDAO.findAll();
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// save or update the customer
		customerDAO.saveCustomer(theCustomer);

	}

	@Override
	public Customer findCustomerById(int theId) {
		
		return customerDAO.findCustomerById(theId);
	}

	@Override
	public void deleteCustomerById(int theId) {
		
		customerDAO.deleteCustomerById(theId);

	}

}
