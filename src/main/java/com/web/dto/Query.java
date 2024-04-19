package com.web.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Query {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Customer customer;
	
	@NotBlank(message = "Please write your query")
	private String addquery;
	private LocalDateTime createdAt;
	private String status;
	
	@ManyToOne
	private Employee employee;
	
	
	
}
