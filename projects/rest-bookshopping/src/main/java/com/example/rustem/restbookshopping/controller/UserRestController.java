package com.example.rustem.restbookshopping.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rustem.restbookshopping.request.LibrarianAddRequest;
import com.example.rustem.restbookshopping.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserRestController {

	private final UserService service;

	@GetMapping(path = "/login")
	public void loginPage() {

	}

	@PostMapping
	public ResponseEntity<Object> add(@Valid @RequestBody LibrarianAddRequest add, BindingResult br) {
		ResponseEntity<Object> resp = service.add(add, br);
		return resp;
	}
}
