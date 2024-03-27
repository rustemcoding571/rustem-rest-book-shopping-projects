package com.example.rustem.restbookshopping.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.rustem.restbookshopping.entity.Book;
import com.example.rustem.restbookshopping.entity.User;
import com.example.rustem.restbookshopping.exception.OurRuntimeException;
import com.example.rustem.restbookshopping.repository.BookRepository;
import com.example.rustem.restbookshopping.request.BookAddRequest;
import com.example.rustem.restbookshopping.response.BookDeleteResponse;
import com.example.rustem.restbookshopping.response.BookDeleteResponseList;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository repository;

	private final SecurityService securityService;

	private final UserService userService;

	private final ModelMapper mapper;

	public ResponseEntity<Object> add(@Valid BookAddRequest book, BindingResult br) {
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, null);
		}
		User user = userService.username(securityService.findByUsername());
		String username = user.getUsername();

		Book books = new Book();
		mapper.map(book, books);
		books.setCreatorUsername(username);
		repository.save(books);
		return ResponseEntity.ok(books);
	}

	public ResponseEntity<Object> get(Integer begin, Integer length) {
		User user = userService.username(securityService.findByUsername());
		String creatorUsername = user.getUsername();
		List<Book> pagination = repository.findPagination(begin, length);
		List<Book> books = repository.CreatorUsername(creatorUsername);

		// response
		return ResponseEntity.ok(books);
	}

	public ResponseEntity<Object> delete(Integer id) {
		Book book = repository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("bele bir kitab tapilmadi"));
		repository.deleteById(id);
		//Response
		List<BookDeleteResponseList> responses = new ArrayList<>();
		BookDeleteResponseList list = new BookDeleteResponseList();
		mapper.map(book, list);
		responses.add(list);
		BookDeleteResponse response = new BookDeleteResponse();
		response.setList(responses);
		response.setMessage("bu kitab silindi!");
		return ResponseEntity.ok(response);
	}

}
