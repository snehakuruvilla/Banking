package com.banking.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.bean.Customer;
import com.banking.repo.CustomerRepository;

/**
 * This service class is to deal with the customer details
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger log = LogManager.getLogger(CustomerServiceImpl.class);

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Optional<Customer> getUser(int customerId) {
		log.info("Checking if customer exists!!");
		return customerRepository.findById(customerId);
	}

}
