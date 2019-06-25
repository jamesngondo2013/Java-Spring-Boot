package com.luv2code.springboot.springbootcrudrestapidemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.springbootcrudrestapidemo.dao.CustomerDAO;
import com.luv2code.springboot.springbootcrudrestapidemo.entity.Customer;

@Service
public class CustomerServiceHibernateImpl implements CustomerService {

	// need to inject customer dao
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> findAll() {
		return customerDAO.findAll();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {

		customerDAO.saveCustomer(theCustomer);
	}

	@Override
	@Transactional
	public Customer findCustomerById(int theId) {
		
		return customerDAO.findCustomerById(theId);
	}

	@Override
	@Transactional
	public void deleteCustomerById(int theId) {
		
		customerDAO.deleteCustomerById(theId);
		
	}
}





