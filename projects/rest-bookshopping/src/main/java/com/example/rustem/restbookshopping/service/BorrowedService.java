package com.example.rustem.restbookshopping.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.rustem.restbookshopping.entity.Book;
import com.example.rustem.restbookshopping.entity.BorrowedBook;
import com.example.rustem.restbookshopping.entity.Student;
import com.example.rustem.restbookshopping.entity.User;
import com.example.rustem.restbookshopping.exception.OurRuntimeException;
import com.example.rustem.restbookshopping.repository.BookRepository;
import com.example.rustem.restbookshopping.repository.BorrowedBookRepository;
import com.example.rustem.restbookshopping.repository.StudentRepository;
import com.example.rustem.restbookshopping.request.BorrowedBookRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BorrowedService {

	private final BorrowedBookRepository repository;

	private final StudentRepository studentRepository;

	private final BookRepository bookRepository;

	private final SecurityService securityService;

	private final UserService userService;

	private final ModelMapper mapper;

	public ResponseEntity<BorrowedBook> add(BorrowedBookRequest request) {

		BorrowedBook borrowedBook = new BorrowedBook();

		User user = userService.username(securityService.findByUsername());
		String username = user.getUsername();
		borrowedBook.setFromWhom(username);

		Integer studentId = request.getStudentId();
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new OurRuntimeException(null, "telebe tapilmadi " + studentId));
		borrowedBook.setStudentId(studentId);
		borrowedBook.setStudentName(student.getName());
		//
		Integer bookId = request.getBookId();
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new OurRuntimeException(null, "telebe tapilmadi" + bookId));
		borrowedBook.setBookId(bookId);
		borrowedBook.setBookName(book.getName());
		repository.save(borrowedBook);

		return ResponseEntity.ok(borrowedBook);
	}

	public ResponseEntity<Object> findAll() {
		User user = userService.username(securityService.findByUsername());
		String username = user.getUsername();
		List<BorrowedBook> list = repository.findAllByFromWhom(username);
		return ResponseEntity.ok(list);
	}

}
