package com.banking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.banking.bean.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	/**
	 * Check for record exists for the customer with account type as C
	 * @param customerId
	 * @return
	 */
	@Query(value = "SELECT * FROM ACCOUNT WHERE cust_id = :custId and account_type = 'C'", nativeQuery = true)
	Account findAccountsByCustomerNative(@Param("custId") int customerId);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO ACCOUNT(cust_id,account_type,balance) VALUES(:custId,:accountType,:balance)", nativeQuery = true)
	void insertAccountNative(@Param("custId") int customerId, @Param("accountType") String accountType,
			@Param("balance") long amount);

}