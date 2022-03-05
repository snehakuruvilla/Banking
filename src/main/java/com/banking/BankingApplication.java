package com.banking;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingApplication {

	private static final Logger log = LogManager.getLogger(BankingApplication.class);
	  
	public static void main(String[] args) {
		log.info("Running the Main Method!!");
		SpringApplication.run(BankingApplication.class, args);  
		

		
	}

}
