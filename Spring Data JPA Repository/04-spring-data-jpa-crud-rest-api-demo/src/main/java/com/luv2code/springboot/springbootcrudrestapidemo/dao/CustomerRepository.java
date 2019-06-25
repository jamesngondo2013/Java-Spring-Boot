package com.luv2code.springboot.springbootcrudrestapidemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.springbootcrudrestapidemo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	
}
