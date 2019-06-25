package com.luv2code.springboot.springbootcrudrestapidemo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.springbootcrudrestapidemo.entity.Customer;
import com.luv2code.springboot.springbootcrudrestapidemo.exception.CustomerNotFoundException;
import com.luv2code.springboot.springbootcrudrestapidemo.service.CustomerService;


@RestController
@RequestMapping("/")
public class CustomerRestController {

	//Here CustomerService delegates the calls to CustomerDAO
	private CustomerService customerService;
	
	//inject customerService dao - constructor inection
	@Autowired
	public CustomerRestController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	//expose "/customers" and return list of customers
	@GetMapping("/customers")
	public List<Customer> findAll(){
		
		return customerService.findAll();
		
		
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId){
		
		Customer theCustomer = customerService.findCustomerById(customerId);
		
		if (theCustomer == null) {
			
			throw new CustomerNotFoundException("Customer ID " + customerId + " not found");
		}
		
		return customerService.findCustomerById(customerId);
		
	}
	
	//add mapping for POST /employees - add new employee
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer){
		
		//also just in case they pass an id in JSON... SET ID TO 0
		//this is to force a save of new item...instead of update/save
		
		theCustomer.setId(0);
		
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
			
	}
	
	@PutMapping("/customers")
	public void saveCustomer(@RequestBody Customer theCustomer){
		
		customerService.saveCustomer(theCustomer);
			
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId){
		
		Customer theCustomer = customerService.findCustomerById(customerId);
		
		if (theCustomer == null) {
			
			throw new CustomerNotFoundException("Customer ID " + customerId + " not found");
		}
		
		customerService.deleteCustomerById(customerId);
		
		return "Customer: " + customerId + " successfully deleted...!!!";
		
	}
}
