package com.example.rustem.restbookshopping.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.rustem.restbookshopping.entity.Book;
import com.example.rustem.restbookshopping.exception.OurRuntimeException;
import com.example.rustem.restbookshopping.repository.BookRepository;
import com.example.rustem.restbookshopping.request.BookAddRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository repository;
	
	private final ModelMapper mapper;

	public ResponseEntity<Object> add(@Valid BookAddRequest book, BindingResult br) {
		if (br.hasErrors()) {
			throw new OurRuntimeException(br,null);
		}
		Book books = new Book();
		mapper.map(book, books);
		repository.save(books);
		return ResponseEntity.ok(books); 
	}
	
}
