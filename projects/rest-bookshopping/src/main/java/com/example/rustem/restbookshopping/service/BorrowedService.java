package com.example.rustem.restbookshopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.rustem.restbookshopping.entity.Book;
import com.example.rustem.restbookshopping.entity.BookGiveStudent;
import com.example.rustem.restbookshopping.entity.BorrowedBook;
import com.example.rustem.restbookshopping.entity.Student;
import com.example.rustem.restbookshopping.entity.User;
import com.example.rustem.restbookshopping.exception.OurRuntimeException;
import com.example.rustem.restbookshopping.repository.BookRepository;
import com.example.rustem.restbookshopping.repository.BorrowedBookRepository;
import com.example.rustem.restbookshopping.repository.ReturnedBookRepository;
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

	private final ReturnedBookRepository returnedBookRepository;

	private final BookGiveStudentService bookGiveStudentService;

	public ResponseEntity<BorrowedBook> add(BorrowedBookRequest request) {

		BorrowedBook borrowedBook = new BorrowedBook();

		User user = userService.username(securityService.findByUsername());
		String username = user.getUsername();
		borrowedBook.setFromWhom(username);

		Integer studentId = request.getStudentId();
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new OurRuntimeException(null, "tələbə tapılmadı " + studentId));
		if (student.getCreatorUsername() == username) {
			borrowedBook.setStudentId(studentId);
			borrowedBook.setStudentName(student.getName());
			borrowedBook.setStudentCreatorUsername(student.getCreatorUsername());
		} else {
			throw new OurRuntimeException(null, "bu tələbini çağırmağa yetkin yoxdur");
		}

		//
		Integer bookId = request.getBookId();
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new OurRuntimeException(null, "tələbə tapılmadı" + bookId));
		if (book.getCreatorUsername() == username) {
			borrowedBook.setBookId(bookId);
			borrowedBook.setBookName(book.getName());
			borrowedBook.setBookCreatorUsername(book.getCreatorUsername());
		} else {
			throw new OurRuntimeException(null, "bu kitabı çağırmağa yetkin yoxdur");
		}
		BookGiveStudent bookGiveStudent = new BookGiveStudent();
		bookGiveStudent.setBookName(book.getName());
		bookGiveStudent.setStudentUsername(student.getUsername());
		bookGiveStudent.setFromWhom(username);
		bookGiveStudentService.save(bookGiveStudent);

		repository.save(borrowedBook);

		return ResponseEntity.ok(borrowedBook);
	}

	public ResponseEntity<Object> findAll() {
		User user = userService.username(securityService.findByUsername());
		String username = user.getUsername();
		List<BorrowedBook> list = repository.findAllByFromWhom(username);
		return ResponseEntity.ok(list);
	}

	public Optional<BorrowedBook> findById(Long id) {
		Optional<BorrowedBook> o = repository.findById(id);
		return o;
	}

}
