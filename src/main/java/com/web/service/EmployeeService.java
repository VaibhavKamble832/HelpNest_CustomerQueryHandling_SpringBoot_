package com.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.web.dto.Employee;
import com.web.repository.EmployeeRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;
	
	public Employee addEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public boolean checkByEmail(String email) {
		return employeeRepo.existsByEmail(email);
	}
	
	public List<Employee> getEmpList(){
		return employeeRepo.findAll();
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
