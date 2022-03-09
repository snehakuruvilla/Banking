###Banking API
####Java Spring Boot Application

#####Description:
The assessment consists of an API to be used for opening a new “current account” of already existing 
customers.

#####Requirements:

* The API will expose an endpoint which accepts the user information (customerID, 
initialCredit).
* Once the endpoint is called, a new account will be opened connected to the user whose ID is 
customerID.
* Also, if initialCredit is not 0, a transaction will be sent to the new account.
* Another Endpoint will output the user information showing Name, Surname, balance, and 
transactions of the accounts.

#####Pre Assumptions:
* The existing users have atleast one of either account (Savings or Current).
* Zero Balance account not acceptable. If the Initial amount is 0, No account will be created.
* The existing accounts will have minimum 1 transaction recorded in the transaction table

#####Tech Stack
* Java
* Spring Boot
* H2 In memory database
* JUnit 5
* HTML 5
* BootStrap 5
* Maven
* Thymeleaf

#####How to run

* $ import the project Banking
* $ convert to maven
* $ run as maven build -- clean install
* $ run as spring boot app
* $ application will be available in: localHost:8080/

#####Table Details:

* CUSTOMER --> To store the existing users
* ACCOUNT --> To store the different accounts of each users
* TRANSACTION --> To store the transaction details of each account

#####The application apis

* GET /v1/user - basic user details 
* POST /v1/account - creates a new account for existing customer
* GET /v1/transaction - retrieves the details of users (customer,account,transaction)

JUnit test are available.
Code quality checked using Sonar.