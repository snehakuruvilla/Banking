package com.banking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.banking.bean.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	@Transactional
	@Modifying
	@Query(value = "insert into Transaction(cust_id,account_id,from_account,description,amount) values(:custId,:accountId,:fromAccount,:description,:amount)", nativeQuery = true)
	void insertTransactionNative(@Param("custId") int customerId, @Param("accountId") int accountId,
			@Param("fromAccount") String fromAccount, @Param("description") String desc, @Param("amount") long amount);

	@Query(value = "SELECT t FROM Transaction t WHERE t.customer.custId = ?1", nativeQuery = false)
	List<Transaction> findTransDetailsNative(int customerId);
}