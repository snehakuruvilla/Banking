/**
 * 
 */
package com.banking.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER")
public class Customer {

	@Id
	@Column(name = "cust_id")
	private int custId;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "customer_surname")
	private String customerSurname;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, targetEntity = Account.class)
	private List<Account> accounts;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, targetEntity = Transaction.class)
	private List<Transaction> transactions;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerSurname() {
		return customerSurname;
	}

	public void setCustomerSurname(String customerSurname) {
		this.customerSurname = customerSurname;
	}

}
