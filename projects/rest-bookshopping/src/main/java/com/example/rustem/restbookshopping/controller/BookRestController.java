package com.example.rustem.restbookshopping.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rustem.restbookshopping.request.BookAddRequest;
import com.example.rustem.restbookshopping.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/books")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BookRestController {

	private final BookService service;

	// books add REST API
	@PostMapping
	public ResponseEntity<Object> add(@Valid @RequestBody BookAddRequest book, BindingResult br) {
		ResponseEntity<Object> resp = service.add(book, br);
		return resp;
	}

	// books get REST API
	@GetMapping
	public ResponseEntity<Object> findAll() {
		ResponseEntity<Object> resp = service.get();
		return resp;
	}

}
