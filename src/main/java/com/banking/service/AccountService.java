package com.banking.service;

import java.util.List;

import com.banking.bean.Account;

public interface AccountService {

	public boolean checkCurrentAccount(int customerId) throws Exception;
	
	public Account createAccount(int customerId, long amount) throws Exception;
	
}
