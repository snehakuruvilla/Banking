package com.banking.service;

import java.util.Optional;

import com.banking.bean.Customer;

public interface CustomerService {

	public Optional<Customer> getUser(int customerId);

}
