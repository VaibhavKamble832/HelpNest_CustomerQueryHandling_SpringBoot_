package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.web.dto.Admin;
import com.web.repository.AdminRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminService {

	@Autowired
	AdminRepo adminRepo;
	
	public Admin registerAdmin(Admin admin) {
		return adminRepo.save(admin);
	}
	
	public boolean checkByEmail(String email) {
		return adminRepo.existsByEmail(email);
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
