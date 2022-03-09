package com.banking.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.banking.bean.Customer;
import com.banking.service.CustomerService;


/**
 * This controller class is used to expose 2 endpoints - 
 * 1. The root mapping
 * 2. /user - which gets the user details
 *
 */
@Controller
public class HomeController {

	private static final Logger log = LogManager.getLogger(HomeController.class);

	@Autowired
	CustomerService customerService;

	/**
	 * This is the root path for the application
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "home";
	}
	
	/**
	 * This endpoint will check whether there is a user exists with the entered customer ID.
	 * If no user, redirected back to home page with valid error message
	 * If user exists, redirect to user details page
	 * @param custId
	 * @return
	 */
	@GetMapping(value = "/user")
	public ModelAndView getUser(int custId) {
		log.info("Checking whether customer exists or not for the customer ID {}." ,custId);
		ModelAndView modelAndView = new ModelAndView();

		try {
				log.info("Customer id : {}." ,custId);
				Optional<Customer> cust = customerService.getUser(custId);
				if (cust.isPresent()) {
					log.info("Customer exists, redirecting user details page");
					modelAndView.setViewName("userData");
					modelAndView.addObject("customer", cust.get());
				} else {
					log.info("Customer does not exist, redirecting back to home page with proper error message");
					modelAndView.setViewName("home");
					modelAndView.addObject("errorMessage", "Customer ID is Invalid");
				}
		} catch (Exception e) {
			log.error(e.toString());
		}
		log.info("Done with checking whether the customer exists or not");
		return modelAndView;
	}

}
