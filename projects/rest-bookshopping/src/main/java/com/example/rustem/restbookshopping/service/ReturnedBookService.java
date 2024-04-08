package com.example.rustem.restbookshopping.service;

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

	private final StudentService studentService;

	private final BookService bookService;

	private final BorrowedBookService borrowedBookService;

//	public ResponseEntity<Object> returned(ReturnedBookRequest req) {
//		ReturnedBook returnedBook = new ReturnedBook();
//
//		Integer id = req.getStudentId();
//		Student student = studentService.findById(id)
//				.orElseThrow(() -> new OurRuntimeException(null, "belə bir tələbə tapılmadı"));
//		returnedBook.setWhichStudentBack(id);
//		returnedBook.setStudentName(student.getName());
//
//		Integer bookId = req.getBookId();
//		Book book = bookService.findById(bookId)
//				.orElseThrow(() -> new OurRuntimeException(null, "belə bir kitab tapılmadı"));
//		returnedBook.setBookId(bookId);
//		returnedBook.setBookName(book.getName());
//		repository.save(returnedBook);
//
//		return ResponseEntity.ok(returnedBook);
//	}

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
}
