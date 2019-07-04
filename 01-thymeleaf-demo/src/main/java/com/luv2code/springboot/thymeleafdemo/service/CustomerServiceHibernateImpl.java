package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.thymeleafdemo.dao.CustomerRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Customer;


@Service
public class CustomerServiceHibernateImpl implements CustomerService {

	private CustomerRepository customerRepository;
	
	// need to inject customer customerRepository
	//CustomerService => CustomerRepository => JPA
	@Autowired
	public CustomerServiceHibernateImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	@Transactional
	public List<Customer> findAll() {
		
		return customerRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {

		customerRepository.save(theCustomer);
	}

	@Override
	@Transactional
	public Customer findCustomerById(int theId) {
		
		Optional<Customer> result = customerRepository.findById(theId);
		
		Customer theCustomer = null;
		
		if (result.isPresent()) {
			
			theCustomer = result.get();
		}
		else {
			
			//throw new CustomerNotFoundException("Customer ID " + theId + " not found");
		}
		return theCustomer;
	}

	@Override
	@Transactional
	public void deleteCustomerById(int theId) {
		
		customerRepository.deleteById(theId);
		
	}
}





