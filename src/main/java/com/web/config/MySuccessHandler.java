package com.web.config;

import java.io.IOException;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MySuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		Set<String> roles=AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		System.out.println(roles);
		if(roles.contains("ROLE_ADMIN")) {
			System.out.println("Adminn loginnnnn");
			response.sendRedirect("/admin/home");
		}
		else if(roles.contains("ROLE_EMPLOYEE")) {
			System.out.println("Employeee loginnnnn");
			response.sendRedirect("/employee/home");
		}
		else {
			System.out.println("Customer loginnnnn");
			response.sendRedirect("/customer/home");
		}
	}
		



}

	
	
