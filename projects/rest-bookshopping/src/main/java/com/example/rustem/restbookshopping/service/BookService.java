package com.example.rustem.restbookshopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.rustem.restbookshopping.entity.Book;
import com.example.rustem.restbookshopping.entity.User;
import com.example.rustem.restbookshopping.exception.OurRuntimeException;
import com.example.rustem.restbookshopping.repository.BookRepository;
import com.example.rustem.restbookshopping.request.BookAddRequest;
import com.example.rustem.restbookshopping.request.BookUpdateRequest;
import com.example.rustem.restbookshopping.response.BookDeleteResponse;
import com.example.rustem.restbookshopping.response.BookDeleteResponseList;
import com.example.rustem.restbookshopping.response.BookUpdateListResponse;
import com.example.rustem.restbookshopping.response.BookUpdateResponse;

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

	public ResponseEntity<Object> get() {
		User user = userService.username(securityService.findByUsername());
		String creatorUsername = user.getUsername();
		List<Book> books = repository.CreatorUsername(creatorUsername);
		// response
		return ResponseEntity.ok(books);
	}

	public ResponseEntity<Object> delete(Integer id) {
		if (id == null || id <= 0) {
			throw new OurRuntimeException(null, "id-ni dogru daxil edin");
		}
		User user = userService.username(securityService.findByUsername());
		String username = user.getUsername();
		Book book = repository.findById(id)
				.orElseThrow(() -> new OurRuntimeException(null, "bele bir kitab tapilmadi"));
		if (book.getCreatorUsername() == username) {
			repository.deleteById(id);
		} else {
			throw new OurRuntimeException(null, "bunu sile bilmezsen");
		}
		// Response
		List<BookDeleteResponseList> responses = new ArrayList<>();
		BookDeleteResponseList list = new BookDeleteResponseList();
		mapper.map(book, list);
		responses.add(list);
		BookDeleteResponse response = new BookDeleteResponse();
		response.setList(responses);
		response.setMessage("bu kitab silindi!");
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<Object> update(@Valid BookUpdateRequest update, BindingResult br) {
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, null);
		}
		User user = userService.username(securityService.findByUsername());
		String username = user.getUsername();
		Book book = repository.findById(update.getBookId())
				.orElseThrow(() -> new OurRuntimeException(null, "bele bir kitab tapilmadi"));
		if (book.getCreatorUsername() == username) {
			mapper.map(update, book);
			repository.save(book);
		} else {
			throw new OurRuntimeException(null, "bunu update ede bilmezsen");
		}
		// response
		List<BookUpdateListResponse> list = new ArrayList<>();
		BookUpdateListResponse updateListResponse = new BookUpdateListResponse();
		mapper.map(update, updateListResponse);
		list.add(updateListResponse);
		BookUpdateResponse response = new BookUpdateResponse();
		response.setMessage("id-si: " + update.getBookId() + " olan kitab guncellendi");
		response.setList(list);
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<Object> findAllPagination(Integer begin, Integer length) {
		List<Book> pagination = repository.findPagination(begin, length);
		return ResponseEntity.ok(pagination);
	}

	public Optional<Book> findById(Integer bookId) {
		Optional<Book> b = repository.findById(bookId);
		return b;
	}

}
