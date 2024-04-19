package com.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.dto.Employee;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

	public boolean existsByEmail(String email);
	
	public Employee findByEmail(String email);
	
}
