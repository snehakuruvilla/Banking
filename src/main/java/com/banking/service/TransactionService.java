package com.banking.service;

import java.util.List;
import java.util.Map;

import com.banking.bean.Transaction;

public interface TransactionService {

	public void createCurrentAccount(int customerId, long amount, int accountId);

	public List<Transaction> findAllTransactionsByCustomer(int customerId) throws Exception;

	public Map<String, Long> findBalance(List<Transaction> tranList) throws Exception;

}
