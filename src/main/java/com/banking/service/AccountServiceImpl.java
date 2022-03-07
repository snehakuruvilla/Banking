package com.banking.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.bean.Account;
import com.banking.repo.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger log = LogManager.getLogger(AccountServiceImpl.class);

	@Autowired
	AccountRepository accountRepository;

	@Override
	public boolean checkCurrentAccount(int customerId) throws Exception {
		log.info("Entering the checkCurrentAccount of AccountServiceImpl class!!");
		log.debug("Checking if Customer has current Account!!");
		boolean flag = false;
		try {
			Account account = accountRepository.findAccountsByCustomerNative(customerId);
			if (account!=null)
				flag = false;
			else
				flag = true;
		} catch (Exception e) {
			log.error(e.toString());
		}
		log.info("Exiting the checkCurrentAccount of AccountServiceImpl class!!");
		return flag;
	}

	@Override
	public Account createAccount(int customerId, long amount) throws Exception {
		log.info("Entering the createAccount of AccountServiceImpl class!!");
		log.debug("Creating current Account for the customer!!");
		Account account = null;
		try {
			accountRepository.insertAccountNative(customerId,"C", amount);
			account = accountRepository.findAccountsByCustomerNative(customerId);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
		}
		return account;
	}


}
