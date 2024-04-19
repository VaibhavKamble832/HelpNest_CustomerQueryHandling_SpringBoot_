package com.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.dto.Admin;
import com.web.dto.Employee;
import com.web.repository.AdminRepo;
import com.web.service.AdminService;
import com.web.service.CustomerService;
import com.web.service.EmployeeService;
import com.web.service.QueryService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	QueryService queryService;

	@ModelAttribute
	public void globalAdmin(Principal p, Model model) {
		if (p != null) {
			String email = p.getName();
			Admin myadmin = adminRepo.findByEmail(email);
			model.addAttribute("myadmin", myadmin);
		}
	}
	
	@GetMapping("/admin/addEmployee")
	public String customerRegisterePage(Model model) {
		model.addAttribute("employee", new Employee());
		return "addEmployee";
	}
	
	@PostMapping("/admin/addEmployee")
	public String registerCustomer(@Valid Employee employee, BindingResult bindingResult, HttpSession session) {
		
		System.out.println("Employee tryyy  to regisret...");
		
		 boolean checkEmail = employeeService.checkByEmail(employee.getEmail());
	        if (checkEmail) {
	            session.setAttribute("emsg", "Email id already exists");
	            System.out.println("duplicate emaillll");
	            return "addEmployee";
	        } 
	        else {
	            if (bindingResult.hasErrors()) {
	            	System.out.println("validationnnnn..");
	                return "addEmployee";
	            }
	            else {
	            	employee.setPassword(passwordEncoder.encode(employee.getPassword()));
	                Employee empobj = employeeService.addEmployee(employee);
	                System.out.println(empobj);
	                session.setAttribute("msg", "Employee added successfully");
	                return "redirect:/admin/addEmployee";
	            }
	        }
	}

	
	
	
	@GetMapping("/admin/emplist")
	public String empList(Model model) {
		model.addAttribute("emplist", employeeService.getEmpList());
		return "admin_emplist";
	}

	@GetMapping("/admin/customerlist")
	public String customerList(Model model) {
		model.addAttribute("customerlist", customerService.getAllCustomerList());
		return "admin_customerlist";
	}

	@GetMapping("/admin/querylist")
	public String queryList(Model model) {
		model.addAttribute("querylist", queryService.getAllQuerList());
		return "admin_querylist";
	}
	
	
	


}
