package com.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.dto.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	public boolean existsByEmail(String email);
	
	public Customer findByEmail(String email);
	
//	public List<Customer> findAllCustomers();
	
}
