package com.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.dto.Employee;
import com.web.dto.Query;
import com.web.repository.EmployeeRepo;
import com.web.service.CustomerService;
import com.web.service.EmployeeService;
import com.web.service.QueryService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	QueryService queryService;
	
	@ModelAttribute
	public void globalCustomer(Principal p, Model model) {
		if(p != null) {
			String email = p.getName();
			Employee myEmployee= employeeRepo.findByEmail(email);
				model.addAttribute("myemployee", myEmployee);
		}
	}
	
	
	
	@GetMapping("/employee/customerlist")
	public String customerList(Model model) {
		model.addAttribute("customerList", customerService.getAllCustomerList());
		return "employee_customerList";
	}
	
	@GetMapping("/employee/queryList/{employee_id}")
	public String queryListPage(@PathVariable(value = "employee_id") Integer employee_id,Model model) {
		model.addAttribute("queryList", queryService.getQuerListByEmployee(employee_id));
		return "employee_queryList";
	}
	
	
	@GetMapping("/employee/readQuery/{q_id}")
	public String readQuery(@PathVariable(value = "q_id") Integer q_id, HttpSession session) {
			Query query=queryService.getQueryById(q_id);
			query.setStatus("read");
			Query readedQuery = queryService.addQuery(query);
			int emp_id = readedQuery.getEmployee().getId();
			
		   if(readedQuery.getStatus()=="read") {
			   System.out.println(query.getStatus());
			   session.setAttribute("msg", "query read succesfully");
		   }
		   else {
			   session.setAttribute("emsg", "something went wrong");
		   }
		  
		   return "redirect:/employee/queryList/"+ emp_id; 
	}
	
	
	@GetMapping("/employee/dumpQuery/{q_id}")
	public String dumpQuery(@PathVariable(value = "q_id") Integer q_id, HttpSession session) {
		Query query=queryService.getQueryById(q_id);
		query.setStatus("dump");
		Query readedQuery = queryService.addQuery(query);
		int emp_id = readedQuery.getEmployee().getId();
		
		if(readedQuery.getStatus()=="dump") {
			System.out.println(query.getStatus());
			session.setAttribute("msg", "query dumped succesfully");
		}
		else {
			session.setAttribute("emsg", "something went wrong");
		}
		
		return "redirect:/employee/queryList/"+ emp_id; 
	}
	
	
}
