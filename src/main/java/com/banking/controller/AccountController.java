package com.banking.controller;

import java.util.List;
import java.util.Map;

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

/**
 * This controller exposes the account and transaction related endpoints -
 * 1. /account - it will check for the current account and also creates an account
 * 2. /transaction - to display all the transaction details including the account balance
 *
 */
@Controller
public class AccountController {

	private static final Logger log = LogManager.getLogger(AccountController.class);

	@Autowired
	AccountService accountService;

	@Autowired
	TransactionService transactionService;

	/**
	 * This method is used to create current account based on the basic criteria that 
	 * there shouldn't be a current account already exists
	 * @param customer
	 * @param amount
	 * @return
	 */
	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public ModelAndView createCurrentAccount(@ModelAttribute Customer customer, long amount) {
		log.info("Checking whether the user already have a current account!!");

		ModelAndView modelAndView = new ModelAndView();
		try {
			if (customer != null && amount > 0) {
				int custId = customer.getCustId();
				log.info("Customer ID " + custId);
				log.info("amount " + amount);

				boolean currentAccountExists = accountService.checkCurrentAccount(custId);
				if (!currentAccountExists) {
					log.info("Creating current account for customer - "+custId);
					
					Account account = accountService.createAccount(custId, amount);
					transactionService.createCurrentAccount(custId, amount, account.getAccountId());
					modelAndView.setViewName("account");
					modelAndView.addObject("customer", customer);
					
					log.info("Current account created with account ID - "+account.getAccountId()+" and inserted to transaction table");
				} else {
					modelAndView.setViewName("accountCreated");
					modelAndView.addObject("errorMessage", "Current Account already exists for this customer");
				}
			} 

		} catch (Exception e) {
			log.error(e.toString());
		}
		return modelAndView;
	}

	/**
	 * This method is used to get all the transaction for the user from both savings and current account
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/transaction", method = RequestMethod.GET)
	public ModelAndView displayCustomerDetails(@ModelAttribute Customer customer) {
		log.info("Getting transaction details");

		ModelAndView modelAndView = new ModelAndView();

		try {
			if (customer != null) {
				log.info("Customer id : " + customer.getCustId());

				List<Transaction> tranList = transactionService.findAllTransactionsByCustomer(customer.getCustId());
				log.info("Number of transactions for the user - "+tranList.size());
				
				if (tranList != null && !tranList.isEmpty()) {
					Map<String, Long> balanceMap = transactionService.findBalance(tranList);
					
					modelAndView.setViewName("transaction");
					modelAndView.addObject("tranList", tranList);
					modelAndView.addObject("balanceMap", balanceMap);
				} else {
					modelAndView.setViewName("accountCreated");
					modelAndView.addObject("errorMessage", "There are no transactions found for this customer");
				}
			} 

		} catch (Exception e) {
			log.error(e.toString());
		}
		log.info("Exiting from displayCustomerDetails method of AccountController!");
		return modelAndView;
	}

}
