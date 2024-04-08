package com.example.rustem.restbookshopping.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rustem.restbookshopping.request.ReturnTimeRequest;
import com.example.rustem.restbookshopping.service.ReturnTimeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/return-time")
@RequiredArgsConstructor
public class ReturnTimeRestController {

	private final ReturnTimeService service;

	@PostMapping
	public ResponseEntity<Object> studentGivenBookReturned(@RequestBody ReturnTimeRequest req) {
		ResponseEntity<Object> resp = service.studentGivenBookReturned(req);
		return resp;
	}
}
