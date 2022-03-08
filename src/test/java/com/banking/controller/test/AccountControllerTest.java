package com.banking.controller.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.banking.bean.Customer;
import com.banking.service.AccountService;
import com.banking.test.BaseTest;

public class AccountControllerTest extends BaseTest{
	
	@Autowired
	private AccountService accountService;
	
	/**
	 * This test is to make sure that the service returns
	 * an error message if the current account is already exists for the user
	 * @throws Exception 
	 */
	@Test
	public void testAccountExists() throws Exception {
		Customer cutsomer = new Customer();
		cutsomer.setCustId(111);
		
		mockMvc.perform(post("/account")
				.param("amount", "100")
				.flashAttr("customer", cutsomer))
					.andExpect(model().attribute("errorMessage", "Current Account already exists for this customer"))
					.andExpect(view().name("accountCreated"));
					
	}
	
	/**
	 * This test is to make sure that there is no current account exists for this user
	 * and system will create a current account
	 * @throws Exception 
	 */
	
	@Test 
	public void testNoAccountExists() throws Exception { 
		Customer cutsomer = new Customer(); cutsomer.setCustId(555);
	  
		mockMvc.perform(post("/account") 
				.param("amount", "100")
				.flashAttr("customer", cutsomer)) 
					.andExpect(view().name("account"));
	  
		assertThat(accountService.checkCurrentAccount(555)).isTrue(); 
	  }
	 
	
	/**
	 * This test is to make sure that the service returned an error message
	 * and redirected to the corresponding pag in case there are no transactions found for the user
	 * @throws Exception 
	 */
	@Test
	public void testNoTransactions() throws Exception {
		Customer cutsomer = new Customer();
		cutsomer.setCustId(5);
		
		mockMvc.perform(get("/transaction")
				.flashAttr("customer", cutsomer))
					.andExpect(model().attribute("errorMessage", "There are no transactions found for this customer"))
					.andExpect(view().name("accountCreated"));
	}
	
	/**
	 * This test is to make sure that transaction details are returned correct
	 * @throws Exception 
	 */
	@Test
	public void testTransactions() throws Exception {
		Customer cutsomer = new Customer();
		cutsomer.setCustId(111);
		
		mockMvc.perform(get("/transaction")
				.flashAttr("customer", cutsomer))
					.andExpect(view().name("transaction"));
	}
	
}
