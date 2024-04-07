package com.example.rustem.restbookshopping.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rustem.restbookshopping.service.BookGiveStudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/book-give-student")
@RequiredArgsConstructor
public class BookGiveStudentRestController {

	private final BookGiveStudentService service;

	@GetMapping
	public ResponseEntity<Object> findAll() {
		ResponseEntity<Object> resp = service.findAll();
		return resp;
	}
}
