package com.banking.service;

import java.util.List;

import com.banking.bean.Transaction;

public interface TransactionService {

	public void createCurrentAccount(int customerId, long amount, int accountId);
	
}
