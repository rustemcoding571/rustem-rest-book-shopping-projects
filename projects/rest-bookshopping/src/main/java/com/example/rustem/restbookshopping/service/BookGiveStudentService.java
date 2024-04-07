package com.example.rustem.restbookshopping.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.rustem.restbookshopping.entity.BookGiveStudent;
import com.example.rustem.restbookshopping.entity.User;
import com.example.rustem.restbookshopping.repository.BookGiveStudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookGiveStudentService {

	private final BookGiveStudentRepository repository;

	private final SecurityService securityService;

	private final UserService userService;

	public void save(BookGiveStudent bookGiveStudent) {
		repository.save(bookGiveStudent);

	}

	public ResponseEntity<Object> findAll() {
		User user = userService.username(securityService.findByUsername());
		String username = user.getUsername();
		List<BookGiveStudent> list = repository.findAllByFromWhom(username);
		return ResponseEntity.ok(list);
	}

}
