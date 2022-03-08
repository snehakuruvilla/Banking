package com.banking.controller.test;

import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import com.banking.test.BaseTest;


public class HomeControllerTest extends BaseTest{

	
	/**
	 * This test method is to test the root mapping is redirecting to the home page
	 * @throws Exception
	 */
	@Test
	public void testIndex() throws Exception {
		mockMvc.perform(get("/"))
					.andExpect(view().name("home"));
	}

	/**
	 * This test is to test the success scenario for getting the user details by customer ID
	 * @throws Exception
	 */
	@Test
	public void testGetUserSuccess() throws Exception {
		mockMvc.perform(get("/user")
				.param("custId", "111"))
					.andExpect(model().attribute("customer", hasProperty("custId", Matchers.equalTo(111))))
					.andExpect(model().attribute("customer", hasProperty("customerName", Matchers.equalTo("TestName1"))))
					.andExpect(view().name("userData"));

	}

	/**
	 * This test is to test that a proper error message 
	 * is returned if user does not exists
	 * @throws Exception
	 */
	@Test
	public void testNoUser() throws Exception {
		mockMvc.perform(get("/user")
				.param("custId", "345"))
					.andExpect(model().attribute("errorMessage", "Customer ID is Invalid"))
					.andExpect(view().name("home"));
	}
	
}
