package com.banking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bean.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>  
{  
}