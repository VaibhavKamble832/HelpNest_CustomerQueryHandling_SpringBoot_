package com.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.web.dto.Query;
import com.web.repository.QueryRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class QueryService {

	@Autowired
	QueryRepo queryRepo;
	
	public Query addQuery(Query query) {
		return queryRepo.save(query);
	}
	
	public List<Query> getAllQuerList(){
		return queryRepo.findAll();
	}
	
	public List<Query> getQuerListByCustomer(int customer_id){
		return queryRepo.findAllByCustomerId(customer_id);
	}

	public List<Query> getQuerListByEmployee(int employee_id){
		return queryRepo.findAllByEmployeeId(employee_id);
	}
	
	public Query getQueryById(int id) {
		return queryRepo.findById(id).get();
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
