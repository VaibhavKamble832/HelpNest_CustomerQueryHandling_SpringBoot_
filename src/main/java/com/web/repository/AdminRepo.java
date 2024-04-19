package com.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.dto.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer>{

	public boolean existsByEmail(String email);
	
	public Admin findByEmail(String email);
}
