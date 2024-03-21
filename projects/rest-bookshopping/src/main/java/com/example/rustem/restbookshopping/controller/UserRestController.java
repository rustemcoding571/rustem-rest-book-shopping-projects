package com.example.rustem.restbookshopping.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rustem.restbookshopping.request.LibrarianAddRequest;
import com.example.rustem.restbookshopping.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserRestController {

	private final UserService service;

	@PostMapping
	public ResponseEntity<Object> add(@Valid @RequestBody LibrarianAddRequest add, BindingResult br){
		ResponseEntity<Object> resp = service.add(add,br);
		return resp;
	}
}
