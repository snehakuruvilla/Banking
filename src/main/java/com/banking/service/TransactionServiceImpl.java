package com.banking.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.bean.Transaction;
import com.banking.repo.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	private static final Logger log = LogManager.getLogger(TransactionServiceImpl.class);

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public void createCurrentAccount(int customerId, long amount, int accountId) {
		log.info("Entering the createCurrentAccount of TransactionServiceImpl class!!");
		log.debug("Creating current Account for the customer!!");
		try {
		transactionRepository.insertTransactionNative(customerId,accountId,"Bank","Joning Current Account Bonus", amount);
		}catch(Exception e) {
			log.error(e.toString());
		}
	}

}
