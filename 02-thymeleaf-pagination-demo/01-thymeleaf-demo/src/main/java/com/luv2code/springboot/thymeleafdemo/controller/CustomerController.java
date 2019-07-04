package com.luv2code.springboot.thymeleafdemo.controller;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Customer;
import com.luv2code.springboot.thymeleafdemo.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	// Here CustomerService delegates the calls to CustomerDAO
	private CustomerService customerService;

	// inject customerService dao - constructor inection
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	// create a mapping for "/customers"
	@GetMapping("/list")
	public String listCustomers(Model theModel, @RequestParam("page") Optional<Integer> page, 
		      @RequestParam("size") Optional<Integer> size) {
		
		int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
 
        Page<Customer> customers = customerService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        

		// List<Customer> theCustomers = customerService.findAll();

		//theModel.addAttribute("customers", theCustomers);
        theModel.addAttribute("customers", customers);
        
        int totalPages = customers.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            theModel.addAttribute("pageNumbers", pageNumbers);
        }

		return "customers/list-customers";
	}

	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data 
		
		Customer theCustomer = new Customer();

		theModel.addAttribute("customer", theCustomer);

		return "customers/customer-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {

		//get the employee from service
		Customer theCustomer = customerService.findCustomerById(theId);
		
		// set customer as the model attribute to pre-populated the form 
		theModel.addAttribute("customer", theCustomer);

		return "customers/customer-form";
	}

	@PostMapping("/save")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer theCustomer,
			BindingResult theBindingResult) {

		if (theBindingResult.hasErrors()) {
			
			return "customers/customer-form";
			
		} else {

			// save the customer using our service
			customerService.saveCustomer(theCustomer);

			//use a redirect to avoid dulicate submissions
			return "redirect:/customers/list";
		}

	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {

		Customer theCustomer = customerService.findCustomerById(theId);

		if (theCustomer == null) {

			// throw new CustomerNotFoundException("Customer ID " + customerId + " not
			// found");
		}

		customerService.deleteCustomerById(theId);

		return "redirect:/customers/list";

	}

}
