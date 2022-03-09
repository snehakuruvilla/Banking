package com.banking.service.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.banking.service.AccountService;
import com.banking.test.BaseTest;

public class AccountServiceTest extends BaseTest{

	@Autowired
	private AccountService accountService;

	/**
	 * This test is to make sure that current account check is working fine in the happy path
	 * @throws Exception
	 */
	@Test
	public void testCheckCurrentAccountSuccess() throws Exception {
		boolean accountFlag = accountService.checkCurrentAccount(122);
		Assertions.assertThat(accountFlag).isEqualTo(false);

	}

	@Test
	public void testCheckCurrentAccountFail() throws Exception {
		boolean accountFlag = accountService.checkCurrentAccount(111);
		Assertions.assertThat(accountFlag).isEqualTo(true);

	}

}
