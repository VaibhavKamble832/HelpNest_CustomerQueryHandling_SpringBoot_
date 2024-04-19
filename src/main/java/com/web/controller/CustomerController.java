package com.web.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dto.Customer;
import com.web.dto.Query;
import com.web.repository.CustomerRepo;
import com.web.service.CustomerService;
import com.web.service.EmployeeService;
import com.web.service.QueryService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	QueryService queryService;
	
	@ModelAttribute
	public void globalCustomer(Principal p, Model model) {
		if(p != null) {
			String email = p.getName();
			Customer mycustomer = customerRepo.findByEmail(email);
				model.addAttribute("mycustomer", mycustomer);
		}
	}
	
	
	@GetMapping("/customer/addQuery")
	public String addQueryPage(Model model, Model model2){
		model.addAttribute("query", new Query());
		model2.addAttribute("emplist", employeeService.getEmpList());
		return "addQuery";
	}
	
	@PostMapping("/customer/addQuery")
	public String addQuery(@Valid Query query, BindingResult bindingResult, HttpSession session) {
		
		if (bindingResult.hasErrors()) {
        	System.out.println("validationnnnn..");
            return "addQuery";
        }
        else {
        	query.setCreatedAt(LocalDateTime.now());
        	Query savedquery = queryService.addQuery(query);
            System.out.println(savedquery);
            session.setAttribute("msg", "Your Query has been sent");
            return "redirect:/customer/addQuery";
        }
	}
	
	
	@GetMapping("/customer/changePass")
	public String customerChangePasswordPage() {
		return "customer_changepass";
	}
	
	
	@PostMapping("/customer/changePass")
	public String changePassword(Principal p, @RequestParam("oldPass") String oldPass, 
			@RequestParam("newPass") String newPass, HttpSession session) {
		
		String email = p.getName();
		Customer customer = customerRepo.findByEmail(email);
		
		boolean f = passwordEncoder.matches(oldPass, customer.getPassword());
		
		if(f) {
			customer.setPassword(passwordEncoder.encode(newPass));
			Customer updatePassCustomer = customerService.registerUser(customer);
			
			if (updatePassCustomer != null) {
				session.setAttribute("msg", "Password Changed Successfully");
			}
			else {
				session.setAttribute("emsg", "Something went wrong");
			}
		}
		else {
			session.setAttribute("emsg", "Your old password is not matching");
		}

		return "redirect:/customer/changePass";
	}
	
	
	
	@GetMapping("/customer/editProfile")
	public String editCustomerPage() {
		return "customer_editProfile";
	}
	
	@PostMapping("/customer/editProfile")
	public String editUser(Principal p, @RequestParam("name") String name,
			@RequestParam("contact") String contact, @RequestParam("gender") String gender,
			@RequestParam("email") String email, HttpSession session){
		
		String pEmail = p.getName();
		Customer customer = customerRepo.findByEmail(pEmail);
		
		customer.setName(name);
		customer.setContact(contact);
		customer.setGender(gender);
		customer.setEmail(email);
		
		Customer updatedCustomer = customerService.registerUser(customer);
		
		if (updatedCustomer != null) {
			session.setAttribute("msg", "Profile Updated Successfully...!");
		}
		else {
			session.setAttribute("emsg", "Something went wrong");
		}
		return "redirect:/customer/editProfile";
	}

	
	@GetMapping("/customer/queryList/{customer_id}")
	public String queryListPage(@PathVariable(value = "customer_id") Integer customer_id,Model model) {
		model.addAttribute("queryList", queryService.getQuerListByCustomer(customer_id));
		return "customer_queryList";
	}
	
	
	
	
}
