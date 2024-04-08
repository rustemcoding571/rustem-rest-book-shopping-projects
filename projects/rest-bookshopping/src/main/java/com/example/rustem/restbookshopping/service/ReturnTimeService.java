package com.example.rustem.restbookshopping.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.rustem.restbookshopping.entity.BorrowedBook;
import com.example.rustem.restbookshopping.entity.ReturnTime;
import com.example.rustem.restbookshopping.entity.ReturnedBook;
import com.example.rustem.restbookshopping.exception.OurRuntimeException;
import com.example.rustem.restbookshopping.repository.ReturnTimeRepository;
import com.example.rustem.restbookshopping.request.ReturnTimeRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReturnTimeService {

	private final ReturnTimeRepository repository;

	private final BorrowedBookService borrowedBookService;

	private final ReturnedBookService returnedBookService;

	public ResponseEntity<Object> studentGivenBookReturned(ReturnTimeRequest req) {
		ReturnTime returnTime = new ReturnTime();

		Long id = req.getBorrowedId();
		BorrowedBook borrowedBook = borrowedBookService.findById(id)
				.orElseThrow(() -> new OurRuntimeException(null, "belə bir verilmiş kitab tapılmadı"));
		returnTime.setGivenBook(borrowedBook.getBookName());
		returnTime.setWhichStudent(borrowedBook.getStudentName());

		Integer id1 = borrowedBook.getBookId();
		ReturnedBook returnedBook = returnedBookService.findById(id1)
				.orElseThrow(() -> new OurRuntimeException(null, "belə bir kitab tapılmadı"));
		returnTime.setReturnedBook(returnedBook.getBookName());
		repository.save(returnTime);

		return ResponseEntity.ok(returnTime);
	}
}
