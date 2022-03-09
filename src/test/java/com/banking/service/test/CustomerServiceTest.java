package com.banking.service.test;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.banking.bean.Customer;
import com.banking.repo.CustomerRepository;
import com.banking.service.CustomerServiceImpl;
import com.banking.test.BaseTest;

public class CustomerServiceTest extends BaseTest{

	@Autowired
	private CustomerServiceImpl customerService;

	@Mock
	CustomerRepository customerRepository;

	Customer customer = new Customer();
	Customer customer1 = null;

	@BeforeEach
	public void setup() {
		customer.setCustId(1);
		customer.setCustomerName("TestName1");
	}

	@Test
	public void testCheckCustomerSuccess() throws Exception {

		int customerId = 111;
		Optional<Customer> c = customerService.getUser(customerId);
		Assertions.assertTrue(c.isPresent());
		Customer customer = c.get();
		Assertions.assertEquals(customer.getCustId(),customer.getCustId());
		Assertions.assertEquals(customer.getCustomerName(),customer.getCustomerName());
	}
	
	@Test
	public void testCheckCustomerFail() throws Exception {

		int customerId = 345;
		//doReturn(Optional.of(customer)).when(customerRepository).findById(customerId);
		Optional<Customer> c = customerService.getUser(customerId);
		Assertions.assertFalse(c.isPresent());
	}
	
	
	
	
}
