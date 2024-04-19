package com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;import com.web.dto.Query;

@Repository
public interface QueryRepo extends JpaRepository<Query, Integer>{

	 public List<Query> findAllByCustomerId(int customer_id);
	 
	 public List<Query> findAllByEmployeeId(int employee_id);
	
}
