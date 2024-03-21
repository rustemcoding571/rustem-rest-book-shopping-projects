package com.example.rustem.restbookshopping.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rustem.restbookshopping.request.BookAddRequest;
import com.example.rustem.restbookshopping.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/books")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BookRestController {

	private final BookService service;
	
	public ResponseEntity<Object> add(@Valid @RequestBody BookAddRequest book,BindingResult br){
	      ResponseEntity<Object> resp = service.add(book,br);
	      return resp;
	}
}
