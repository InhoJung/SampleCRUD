package com.raistudies.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.raistudies.domain.Customer;
import com.raistudies.persistence.CustomerService;
import com.raistudies.validator.RegistrationValidator;

@Controller
@RequestMapping(value="/registration", produces = "text/plain;charset=UTF-8")
public class RegistrationController {
	
	private RegistrationValidator validator = null;
	private CustomerService customerService = null;
		
	@Autowired
	public void setUserService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public RegistrationValidator getValidator() {
		return validator;
	}

	@Autowired
	public void setValidator(RegistrationValidator validator) {
		this.validator = validator;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String showForm(ModelMap model){
		System.out.println("[showForm] start");
		List<Customer> customers = customerService.getAllUser();
		System.out.println("[customerService]" + customerService);
		
		
		if(customers != null)
			System.out.println("Customer is " + customers.size());
		model.addAttribute("customers", customers);
		Customer user = new Customer();
//		user.setCustomer_id(UUID.randomUUID().toString());
		model.addAttribute("customer", user);
		return "registration";
	}

	/**
	 * Add a customer to database in the web application
	 * @param customer
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView add(@ModelAttribute(value="customer") Customer customer,BindingResult result){
		System.out.println("[add] start");
		validator.validate(customer, result);
		System.out.println(customer);
		ModelAndView mv = new ModelAndView("registration");
		if(!result.hasErrors()){
			customer.setCustomer_id(UUID.randomUUID().toString());
			customerService.saveUser(customer);
//			customer = new Customer();
			
			mv.addObject("customer", customer);
		}
		mv.addObject("customers", customerService.getAllUser());
		return mv;
	}
	
	/**
	 * Update the customer in a web application
	 * @param customer
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ModelAndView update(@ModelAttribute(value="customer") Customer customer,BindingResult result){
		System.out.println("Update !! " + customer);
		validator.validate(customer, result);
		ModelAndView mv = new ModelAndView("registration");
		if(!result.hasErrors()){			
			customerService.updateUser(customer);
			customer = new Customer();
			customer.setCustomer_id(UUID.randomUUID().toString());
			mv.addObject("customer", customer);
		}
		mv.addObject("customers", customerService.getAllUser());
		return mv;
	}
	
	/**
	 * Remove the customer in a Web Application
	 * @param customer
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute(value="customer") Customer customer,BindingResult result){
		validator.validate(customer, result);
		ModelAndView mv = new ModelAndView("registration");
		if(!result.hasErrors()){
			System.out.println(customer);
			
			customerService.deleteUser(customer.getCustomer_id());
			customer = new Customer();
			customer.setCustomer_id(UUID.randomUUID().toString());
			mv.addObject("customer", customer);
		}
		mv.addObject("customers", customerService.getAllUser());
		return mv;
	}
	
	/**
	 * Add a customer using REST APIs
	 * @param customer
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/customer", method=RequestMethod.POST)
	public @ResponseBody Customer addCustomer(@RequestBody Customer customer, BindingResult result) {
		System.out.println("Create (REST API) !! " + customer);
		validator.validate(customer, result);
		
		if(!result.hasErrors()){
			customer.setCustomer_id(UUID.randomUUID().toString());
			customerService.saveUser(customer);
		}
		return customer;
			
	}	
}
