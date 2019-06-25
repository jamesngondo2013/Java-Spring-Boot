package com.luv2code.springboot.springbootcrudrestapidemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.springbootcrudrestapidemo.entity.Customer;

@Repository
public class CustomerDAOJpaImpl implements CustomerDAO {

	private EntityManager entityManager;
	
	@Autowired
	public CustomerDAOJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Customer> findAll() {
		// create a query
		Query theQuery = entityManager.createQuery("from Customer");
		
		//execuet a query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		//return the result
		return customers;
	}

	@Override
	public Customer findCustomerById(int theId) {
		
		//get Customer
		Customer theCustomer = entityManager.find(Customer.class, theId);
		
		//return Customer
		return theCustomer;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// save or update the customer
		Customer dbCustomer = entityManager.merge(theCustomer);
		
		//update with id from db...so we can get generated id for save/insert
		theCustomer.setId(dbCustomer.getId());

	}

	@Override
	public void deleteCustomerById(int theId) {
		// delete object with primary key
		Query theQuery = entityManager.createQuery("delete from Customer where id=:customerId");
		
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();

	}

}
