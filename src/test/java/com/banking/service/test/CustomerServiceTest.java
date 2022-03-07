package com.banking.service.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.banking.bean.Account;
import com.banking.bean.Customer;
import com.banking.repo.CustomerRepository;
import com.banking.service.CustomerServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class CustomerServiceTest {

	@Autowired
	private CustomerServiceImpl customerService;

	@Mock
	CustomerRepository customerRepository;

	Customer customer = new Customer();
	Customer customer1 = null;

	@BeforeEach
	public void setup() {
		customer.setCustId(111);
		customer.setCustomerName("TestName1");
	}

	@Test
	public void testCheckCustomerSuccess() throws Exception {

		int customerId = 111;
		//doReturn(Optional.of(customer)).when(customerRepository).findById(customerId);
		Customer c = customerService.getUser(customerId);
		Assertions.assertEquals(customer.getCustId(),c.getCustId());
		Assertions.assertEquals(customer.getCustomerName(),c.getCustomerName());
	}
	
	@Test
	public void testCheckCustomerFail() throws Exception {

		int customerId = 345;
		//doReturn(Optional.of(customer)).when(customerRepository).findById(customerId);
		Customer c = customerService.getUser(customerId);
		Assertions.assertEquals(null,c);
	}
	
	
	
	
}