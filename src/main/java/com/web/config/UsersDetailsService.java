package com.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.web.dto.Admin;
import com.web.dto.Customer;
import com.web.dto.Employee;
import com.web.repository.AdminRepo;
import com.web.repository.CustomerRepo;
import com.web.repository.EmployeeRepo;

@Component
public class UsersDetailsService implements UserDetailsService{

	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Customer customer = customerRepo.findByEmail(username);
	
		if (customer == null) {
			Admin admin = adminRepo.findByEmail(username);
			if (admin == null) {
				Employee employee = employeeRepo.findByEmail(username);	
				if(employee == null) {
					throw new UsernameNotFoundException("User not found");
				}
				else {
					return new EmployeeDetails(employee);
				}
            }
			else {
                return new AdminDetails(admin);
            }
		}
		else {
			return new CustomerDetails(customer);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
}
