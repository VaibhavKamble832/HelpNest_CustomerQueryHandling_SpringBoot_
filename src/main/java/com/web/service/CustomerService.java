package com.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.web.dto.Customer;
import com.web.repository.CustomerRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class CustomerService {

	@Autowired
	CustomerRepo customerRepo;
	
	public Customer registerUser(Customer customer) {
		return customerRepo.save(customer);
	}
	
	public boolean checkByEmail(String email) {
		return customerRepo.existsByEmail(email);
	}
	
	public List<Customer> getAllCustomerList(){
		return customerRepo.findAll();
	}
	
	
	public void removeSessionMsg() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		
		session.removeAttribute("msg");
	}
	public void removeSessionEMsg() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		
		session.removeAttribute("emsg");
	}
	
	
}
