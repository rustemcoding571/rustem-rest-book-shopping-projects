package com.example.rustem.restbookshopping.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.rustem.restbookshopping.entity.Authority;
import com.example.rustem.restbookshopping.entity.Student;
import com.example.rustem.restbookshopping.entity.User;
import com.example.rustem.restbookshopping.exception.OurRuntimeException;
import com.example.rustem.restbookshopping.repository.StudentRepository;
import com.example.rustem.restbookshopping.request.StudentAddRequest;
import com.example.rustem.restbookshopping.request.StudentUpdateRequest;
import com.example.rustem.restbookshopping.response.StudentAddResponse;
import com.example.rustem.restbookshopping.response.StudentDeleteResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository repository;

	private final ModelMapper mapper;

	private final AuthorityService authorityService;

	private final UserService userService;

	private final SecurityService securityService;

	public ResponseEntity<Object> addStudent(@Valid StudentAddRequest add, BindingResult br) {
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, null);
		}
		User user = userService.username(securityService.findByUsername());
		String username = user.getUsername();

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<User> check = userService.findByUsername(add.getUsername());
		if (check.isPresent()) {
			throw new OurRuntimeException(null, "bu username artiq istifade olunub");
		}
		Student student = new Student();
		mapper.map(add, student);
		student.setCreatorUsername(username);
		repository.save(student);
		User users = new User();
		mapper.map(add, users);
		String raw = add.getPassword();
		String pass = encoder.encode(raw);
		users.setPassword(pass);
		users.setEnabled(1);
		userService.addStudent(users);
		Authority authority = new Authority();
		authority.setUsername(student.getUsername());
		authority.setAuthority("ROLE_STUDENT");
		authorityService.save(authority);

		// Response
		StudentAddResponse response = new StudentAddResponse();
		mapper.map(student, response);
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<Object> deleteStudent(Integer id) {
		if (id == null || id <= 0) {
			throw new OurRuntimeException(null, "id-ni dogru daxil edin");
		}
		User user = userService.username(securityService.findByUsername());
		String username = user.getUsername();
		Student student = repository.findById(id)
				.orElseThrow(() -> new OurRuntimeException(null, "bele bir telebe tapilmadi"));
		if (student.getCreatorUsername() == username) {
			repository.deleteById(id);
			authorityService.deleteByUsername(student.getUsername());
			userService.deleteByUsername(student.getUsername());
		} else {
			throw new OurRuntimeException(null, "bu telebeni sile bilmezsiz");
		}
		// response
		StudentDeleteResponse response = new StudentDeleteResponse();
		mapper.map(student, response);
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<Object> updateStudent(@Valid StudentUpdateRequest update, BindingResult br) {
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, null);
		}
		User user = userService.username(securityService.findByUsername());
		String username = user.getUsername();
		Optional<User> check = userService.findByUsername(update.getUsername());
		if (check.isPresent()) {
			throw new OurRuntimeException(null, "bu username artiq istifade olunub");
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		Student student = repository.findById(update.getId())
				.orElseThrow(() -> new OurRuntimeException(null, "bele bir telebe tapilmadi"));
		mapper.map(update, student);
		User users = userService.findById(update.getUserId())
				.orElseThrow(() -> new OurRuntimeException(null, "bele bir username yoxdur"));
		users.setUsername(update.getUsername());
		String raw = update.getPassword();
		String pass = encoder.encode(raw);
		users.setPassword(pass);
		Authority authority = authorityService.findById(update.getAuthorityId())
				.orElseThrow(() -> new OurRuntimeException(null, "huquq cedvelinde bele bir username tapilmadi"));
		authority.setUsername(update.getUsername());
		if (student.getCreatorUsername() == username) {
			repository.save(student);
			userService.addStudent(users);
			authorityService.save(authority);
		} else {
			throw new OurRuntimeException(null, "bu telebeni update ede bilmezsen");
		}
		// response
		StudentAddResponse response = new StudentAddResponse();
		mapper.map(update, response);
		return ResponseEntity.ok(response);
	}

}
