package com.example.rustem.restbookshopping.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.rustem.restbookshopping.entity.BorrowedBook;
import com.example.rustem.restbookshopping.entity.ReturnedBook;
import com.example.rustem.restbookshopping.exception.OurRuntimeException;
import com.example.rustem.restbookshopping.repository.ReturnedBookRepository;
import com.example.rustem.restbookshopping.request.ReturnedBookRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReturnedBookService {

	private final ReturnedBookRepository repository;

	private final BorrowedBookService borrowedBookService;

	public ResponseEntity<Object> returned(ReturnedBookRequest req) {
		ReturnedBook returnedBook = new ReturnedBook();

		Long id = req.getBorrowedId();
		BorrowedBook borrowedBook = borrowedBookService.findById(id)
				.orElseThrow(() -> new OurRuntimeException(null, "belə bir verilmiş kitab tapılmadı"));
		returnedBook.setBookId(borrowedBook.getBookId());
		returnedBook.setBookName(borrowedBook.getBookName());
		returnedBook.setWhichStudentBack(borrowedBook.getStudentId());
		returnedBook.setStudentName(borrowedBook.getStudentName());
		repository.save(returnedBook);

		return ResponseEntity.ok(returnedBook);
	}

	public Optional<ReturnedBook> findById(Integer id1) {
		Optional<ReturnedBook> o = repository.findById(id1);
		return o;
	}
}
