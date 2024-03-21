package com.example.rustem.restbookshopping.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

	
	public String findByUsername() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return username;
	}
}
