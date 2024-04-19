package com.web.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Please enter your name")
	private String name;
	
	@NotBlank(message = "Please enter contact number")
    @Size(message = "Please enter 10 digit contact number", min = 10, max = 10)
	private String contact;
	
	@NotBlank(message = "Please select your gander")
	private String gender;
	
	@NotBlank(message = "Please enter mail id")
	private String email;
	
	@NotBlank(message = "Please enter strong password..")
	private String password;
	
	private String role;
}
