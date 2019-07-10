package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.dao.CustomerRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Customer;
import com.luv2code.springboot.thymeleafdemo.service.CustomerService;

//james
//fun123

//gilby
//fun123

//john
//fun123

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	
	@Autowired
	CustomerRepository customerRepository;

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
		
		// Evaluate page size. If requested parameter is null, return initial
		// page size
		int evalPageSize = size.orElse(INITIAL_PAGE_SIZE);
		// Evaluate page. If requested parameter is null or less than 0 (to
		// prevent exception), return initial size. Otherwise, return value of
		// param. decreased by 1.
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		// print repo
		System.out.println("here is client repo " + customerRepository.findAll());
		//Page<ClientModel> clientlist = clientrepository.findAll(new PageRequest(evalPage, evalPageSize));

		// List<Customer> theCustomers = customerService.findAll();
		theModel.addAttribute("customers", customerRepository.findAll(PageRequest.of(evalPage, evalPageSize)));
		//theModel.addAttribute("customers", theCustomers);

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
	
	@GetMapping("/home")
	public String showHome(@Valid @ModelAttribute("customer") Customer theCustomer,
			BindingResult theBindingResult) {
		
		return "customers/home";
	}
	
	@GetMapping("/index")
	public String showIndex(@Valid @ModelAttribute("customer") Customer theCustomer,
			BindingResult theBindingResult) {
		
		return "customers/index";
	}
	
	
	@GetMapping("/user")
	public String user(Model theModel, @ModelAttribute("customer") Customer theCustomer,
			BindingResult theBindingResult) {
		
		return "customers/user";
	}
	
	// add request mapping for /leaders
	@GetMapping("/manager")
	public String showLeaders() {
		
		return "customers/leaders";
	}
	
	@GetMapping("/ceo")
	public String showCEO() {
		
		return "customers/ceo";
	}
	
	// add request mapping for /systems
	
	@GetMapping("/admin")
	public String showSystems() {
		
		return "customers/systems";
	}
	
	@GetMapping("/login")
	public String showMyLoginPage(@Valid @ModelAttribute("customer") Customer theCustomer,
			BindingResult theBindingResult) {

		return "customers/login";
			
	}
	
	@GetMapping("/password")
	public String showPasswordReset(@Valid @ModelAttribute("customer") Customer theCustomer,
			BindingResult theBindingResult) {

		return "customers/password-reset";
			
	}
	
	// add request mapping for /access-denied
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "customers/access-denied";
		
	}
	

}
