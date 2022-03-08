package com.banking.service.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.banking.bean.Account;
import com.banking.bean.Transaction;
import com.banking.service.AccountService;
import com.banking.service.TransactionService;
import com.banking.test.BaseTest;

public class TransactionServiceTest extends BaseTest{

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	AccountService accountService;
	
	/**
	 * This test is used to test the current account creation
	 * @throws Exception
	 */
	@Test
	public void testCreateAccount() throws Exception {
		Account account = accountService.createAccount(666, 100);
		transactionService.createCurrentAccount(666, 100, account.getAccountId());
		List<Transaction> transactions = transactionService.findAllTransactionsByCustomer(666);
		assertThat(transactions)
				.hasSize(1);
	}

	@Test
	public void testFindAllTransactionsByCustomer() throws Exception {
		List<Transaction> transactions = transactionService.findAllTransactionsByCustomer(111);
		assertThat(transactions)
				.hasSize(3)
				.extracting(Transaction::getFromAccount)
				.containsExactlyInAnyOrder("Bank", "Amazon", "Bank");
	}

}
