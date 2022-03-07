package com.banking.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.banking.bean.Customer;
import com.banking.service.CustomerService;

@Controller
public class HomeController {

	private static final Logger log = LogManager.getLogger(HomeController.class);

	@Autowired
	CustomerService customerService;

	@RequestMapping("/")
	public String index() {
		return "home";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ModelAndView getUser(int custId) {
		log.info("Checking whether customer exists or not for the customer ID "+custId);
		ModelAndView modelAndView = new ModelAndView();
			        
		try {
			log.trace("Customer id : "+custId);
			Customer cust = customerService.getUser(custId);
			if (cust != null) {
				modelAndView.setViewName("userData");
				modelAndView.addObject("customer", cust);
			} else {
				modelAndView.setViewName("home");
				modelAndView.addObject("errorMessage","Customer ID is Invalid");
			}
		} catch (Exception e) {
			log.error(e.toString());
		}
		log.info("Done with checking whether the customer exists or not");
		return modelAndView;
	}

}
