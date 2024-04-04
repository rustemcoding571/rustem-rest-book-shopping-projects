package com.example.rustem.restbookshopping.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rustem.restbookshopping.entity.BorrowedBook;
import com.example.rustem.restbookshopping.request.BorrowedBookRequest;
import com.example.rustem.restbookshopping.service.BorrowedService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/borrowedBooks")
@RequiredArgsConstructor
public class BorrowedBooktRestController {

	private final BorrowedService service;

	@PostMapping
	public ResponseEntity<BorrowedBook> borrowedBook(@RequestBody BorrowedBookRequest request) {
		ResponseEntity<BorrowedBook> resp = service.add(request);
		return resp;
	}

	@GetMapping
	public ResponseEntity<Object> findAllBorrowedBook() {
		ResponseEntity<Object> resp = service.findAll();
		return resp;
	}
}
