package com.banking.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.bean.Customer;
import com.banking.repo.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger log = LogManager.getLogger(CustomerServiceImpl.class);

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer getUser(int customerId) {
		log.info("Entering the checkCustomer of CustomerServiceImpl class!!");
		log.debug("Checking if CustomerId is available in the db!!");
		Optional<Customer> c = customerRepository.findById(customerId);
		if (c.isPresent())
			return c.get();
		else
			return null;
	}

}
