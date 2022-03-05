package com.banking.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute Customer customer) {
		log.info("Entering into save method of HomeController!");
			        
		ModelAndView modelAndView = new ModelAndView();

		try {
			log.trace("Customer id :",customer.getCustomerId());
			Customer cust = customerService.checkCustomer(customer.getCustomerId());
			if (cust != null) {
				modelAndView.setViewName("user-data");
				modelAndView.addObject("customer", cust);
			} else {
				modelAndView.setViewName("home");
				modelAndView.addObject("customer", cust);
			}
		} catch (Exception e) {
			log.error(e.toString());
		}
		log.info("Exiting from save method of HomeController!");
		return modelAndView;
	}

}
