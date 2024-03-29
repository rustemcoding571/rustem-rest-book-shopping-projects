package com.example.rustem.restbookshopping.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rustem.restbookshopping.request.StudentAddRequest;
import com.example.rustem.restbookshopping.request.StudentUpdateRequest;
import com.example.rustem.restbookshopping.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/students")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class StudentRestController {

	private final StudentService service;

	// student add REST API
	@PostMapping
	@PreAuthorize(value = " hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Object> addStudent(@Valid @RequestBody StudentAddRequest add, BindingResult br) {
		ResponseEntity<Object> resp = service.addStudent(add, br);
		return resp;
	}

	// student delete REST API
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteStudent(@PathVariable Integer id) {
		ResponseEntity<Object> resp = service.deleteStudent(id);
		return resp;
	}

	// student update REST API
	@PutMapping
	public ResponseEntity<Object> updateStudent(@Valid @RequestBody StudentUpdateRequest update, BindingResult br) {
		ResponseEntity<Object> resp = service.updateStudent(update, br);
		return resp;
	}
}
