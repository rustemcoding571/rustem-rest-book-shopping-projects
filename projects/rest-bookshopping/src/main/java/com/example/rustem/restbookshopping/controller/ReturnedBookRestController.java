package com.example.rustem.restbookshopping.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rustem.restbookshopping.request.ReturnedBookRequest;
import com.example.rustem.restbookshopping.service.ReturnedBookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/returned-book")
@RequiredArgsConstructor
public class ReturnedBookRestController {

	private final ReturnedBookService service;

	@PostMapping
	public ResponseEntity<Object> returnBook(@RequestBody ReturnedBookRequest req) {
		ResponseEntity<Object> resp = service.returned(req);
		return resp;
	}
}
