package com.banking.controller.test;

import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@BeforeAll
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

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
		mockMvc.perform(post("/user")
				.param("custId", "111"))
					.andExpect(model().attribute("customer", hasProperty("custId", Matchers.equalTo(111))))
					.andExpect(model().attribute("customer", hasProperty("customerName", Matchers.equalTo("TestName1"))))
					.andExpect(view().name("userData"));

	}

	@Test
	public void testSavefail() throws Exception {
		mockMvc.perform(post("/user")
				.param("custId", "345"))
					.andExpect(model().attribute("errorMessage", "Customer ID is Invalid"))
					.andExpect(view().name("home"));
	}

}
