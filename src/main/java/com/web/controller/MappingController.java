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
import com.web.dto.Customer;
import com.web.dto.Employee;
import com.web.repository.AdminRepo;
import com.web.repository.CustomerRepo;
import com.web.repository.EmployeeRepo;
import com.web.service.AdminService;
import com.web.service.CustomerService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MappingController {

	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
	
	@GetMapping("/login")
	public String customLoginPage() {
		return "custom_login";
	}
	
	@GetMapping("/customer/home")
	public String customerHome() {
		return "customer_home";
	}
	@GetMapping("/admin/home")
	public String adminHome() {
		return "admin_home";
	}
	@GetMapping("/employee/home")
	public String employeeHome() {
		return "employee_home";
	}
	
	
	@ModelAttribute
	public void globalCustomer(Principal p, Model model) {
		if(p != null) {
			String email = p.getName();
			Customer mycustomer = customerRepo.findByEmail(email);
				model.addAttribute("mycustomer", mycustomer);
		}
	}
	
	@ModelAttribute
	public void globalAdmin(Principal p, Model model) {
		if(p != null) {
			String email = p.getName();
			Admin myAdmin = adminRepo.findByEmail(email);
				model.addAttribute("myadmin", myAdmin);
		}
	}
	
	@ModelAttribute
	public void globalEmployee(Principal p, Model model) {
		if(p != null) {
			String email = p.getName();
			Employee myEmployee= employeeRepo.findByEmail(email);
				model.addAttribute("myemployee", myEmployee);
		}
	}
	
	
	
	@GetMapping("/customerRegister")
	public String customerRegisterePage(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer_register";
	}
	
	@PostMapping("/customerRegister")
	public String registerCustomer(@Valid Customer customer, BindingResult bindingResult, HttpSession session) {
		
		System.out.println("Customerrrr tryyy  to regisret...");
		
		 boolean checkEmail = customerService.checkByEmail(customer.getEmail());
	        if (checkEmail) {
	            session.setAttribute("emsg", "Email id already exists");
	            System.out.println("duplicate emaillll");
	            return "customer_register";
	        } 
	        else {
	            if (bindingResult.hasErrors()) {
	            	System.out.println("validationnnnn..");
	                return "customer_register";
	            }
	            else {
	            	customer.setPassword(passwordEncoder.encode(customer.getPassword()));
	                Customer customerobj = customerService.registerUser(customer);
	                System.out.println(customerobj);
	                session.setAttribute("msg", "Successfully Registered");
	                return "redirect:/customerRegister";
	            }
	        }
	
	}
	
	
	
	
	@GetMapping("/adminRegister")
	public String adminRegisterePage(Model model) {
		model.addAttribute("admin", new Admin());
		return "admin_register";
	}
	
	@PostMapping("/adminRegister")
	public String registerAdmin(@Valid Admin admin, BindingResult bindingResult, HttpSession session) {
		
		System.out.println("Customerrrr tryyy  to regisret...");
		
		 boolean checkEmail = adminService.checkByEmail(admin.getEmail());
	        if (checkEmail) {
	            session.setAttribute("emsg", "Email id already exists");
	            System.out.println("duplicate emaillll");
	            return "admin_register";
	        } 
	        else {
	            if (bindingResult.hasErrors()) {
	            	System.out.println("validationnnnn..");
	                return "admin_register";
	            }
	            else {
	            	admin.setPassword(passwordEncoder.encode(admin.getPassword()));
	                Admin adminobj = adminService.registerAdmin(admin);
	                System.out.println(adminobj);
	                session.setAttribute("msg", "Successfully Registered");
	                return "redirect:/adminRegister";
	            }
	        }
	
	}
	
	
	
	
}
