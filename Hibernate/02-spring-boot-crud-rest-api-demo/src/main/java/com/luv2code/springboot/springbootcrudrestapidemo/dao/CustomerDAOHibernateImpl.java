package com.luv2code.springboot.springbootcrudrestapidemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.springbootcrudrestapidemo.entity.Customer;

/*
 * USING NATIVE HIBERNATE API
 */

@Repository
public class CustomerDAOHibernateImpl implements CustomerDAO{

	//define field for entityManager
	private EntityManager entityManager;
	
	//set up constructor injection - can use any type of injection eg method
	public CustomerDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}	
	
	@Override
	@Transactional
	public List<Customer> findAll() {
		
		// get the hibernate session - import
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create query
		Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class);
		
		//execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		//return the results
		return customers;
	}
	
	//get single customer
	@Override
	@Transactional
	public Customer findCustomerById(int theId) {
		
		// get the hibernate session - import
		Session currentSession = entityManager.unwrap(Session.class);
		
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		
		// get the hibernate session - import
		Session currentSession = entityManager.unwrap(Session.class);
		
		//save customer
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	@Transactional
	public void deleteCustomerById(int theId) {
		
		// get the hibernate session - import
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from Customer where id=:employeeId");
		
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
		
	}

}
