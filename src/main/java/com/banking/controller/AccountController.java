package com.banking.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.banking.bean.Account;
import com.banking.bean.Customer;
import com.banking.bean.Transaction;
import com.banking.service.AccountService;
import com.banking.service.TransactionService;

@Controller
public class AccountController {

	private static final Logger log = LogManager.getLogger(AccountController.class);

	@Autowired
	AccountService accountService;

	@Autowired
	TransactionService transactionService;

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public ModelAndView createCurrentAccount(@ModelAttribute Customer customer, long amount) {
		log.info("Entering into createCurrentAccount method of AccountController!");

		ModelAndView modelAndView = new ModelAndView();

		try {
			log.trace("Customer id :", customer.getCustId());

			boolean accountCheck = accountService.checkCurrentAccount(customer.getCustId());
			if (accountCheck) {
				Account a = accountService.createAccount(customer.getCustId(), amount);
				transactionService.createCurrentAccount(customer.getCustId(), amount,a.getAccountId());
				modelAndView.setViewName("account");
				modelAndView.addObject("customer", customer);
			} else {
				modelAndView.setViewName("account-created");
				modelAndView.addObject("message","Current Account Already Existing");
			}

		} catch (Exception e) {
			log.error(e.toString());
		}
		log.info("Exiting from createCurrentAccount method of AccountController!");
		return modelAndView;
	}
	

}
