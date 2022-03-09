package com.banking.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.bean.Account;
import com.banking.repo.AccountRepository;

/**
 * Service class to deal with the account and transactions
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger log = LogManager.getLogger(AccountServiceImpl.class);

	@Autowired
	AccountRepository accountRepository;

	/**
	 * This method is used to check wether the user have a current account
	 */
	@Override
	public boolean checkCurrentAccount(int customerId) throws Exception {
		boolean flag = false;
		try {
			Account account = accountRepository.findAccountsByCustomerNative(customerId);
			if (account != null)
				flag = true;
		} catch (Exception e) {
			log.error(e.toString());
		}
		log.info("Current account exists - {}.",flag);
		return flag;
	}

	@Override
	public Account createAccount(int customerId, long amount) throws Exception {
		log.info("Entering the createAccount of AccountServiceImpl class!!");
		log.debug("Creating current Account for the customer!!");
		Account account = null;
		try {
			accountRepository.insertAccountNative(customerId, "C", amount);
			account = accountRepository.findAccountsByCustomerNative(customerId);

		} catch (Exception e) {
			log.error(e.toString());
		}
		log.info("Exiting the createAccount of AccountServiceImpl class!!");
		return account;
	}

}
