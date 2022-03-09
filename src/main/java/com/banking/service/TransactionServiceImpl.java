package com.banking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.bean.Transaction;
import com.banking.repo.TransactionRepository;

/**
 * Service class to deal with the transactions
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	private static final Logger log = LogManager.getLogger(TransactionServiceImpl.class);

	@Autowired
	TransactionRepository transactionRepository;

	/**
	 * This method is used to create the current account
	 */
	@Override
	public void createCurrentAccount(int customerId, long amount, int accountId) {
		try {
			log.info("Creating current account for the user - {}.",customerId);
			transactionRepository.insertTransactionNative(customerId, accountId, "Bank", "Joning Current Account Bonus", amount);
		} catch (Exception e) {
			log.error(e.toString());
		}
	}

	/**
	 * This method is used to get all the transaction for the user
	 */
	@Override
	public List<Transaction> findAllTransactionsByCustomer(int customerId) throws Exception {
		List<Transaction> transList = null;
		
		try {
			log.debug("Finding the Transaction details of Customer!");
			transList = transactionRepository.findTransDetailsNative(customerId);

		} catch (Exception e) {
			log.error(e.toString());
		}
		return transList;
	}

	/**
	 * This method is used to find the balance for customer's account
	 */
	public Map<String, Long> findBalance(List<Transaction> tranList) {
		log.info("Entering the findBalance of TransactionServiceImpl class!!");
		HashMap<String, Long> map = new HashMap<>();
		try {
			if (tranList != null) {
				for (Transaction t : tranList) {
					map.put(t.getAccount().getAccountType(), t.getAccount().getBalance());
				}
			}
		} catch (Exception e) {
			log.error(e.toString());
		}
		log.info("Exiting the findBalance of TransactionServiceImpl class!!");
		return map;
	}
}
