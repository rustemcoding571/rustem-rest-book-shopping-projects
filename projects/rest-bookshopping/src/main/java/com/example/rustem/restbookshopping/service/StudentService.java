package com.example.rustem.restbookshopping.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.rustem.restbookshopping.entity.Authority;
import com.example.rustem.restbookshopping.entity.Student;
import com.example.rustem.restbookshopping.exception.OurRuntimeException;
import com.example.rustem.restbookshopping.repository.StudentRepository;
import com.example.rustem.restbookshopping.request.StudentAddRequest;
import com.example.rustem.restbookshopping.response.StudentAddResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository repository;

	private final ModelMapper mapper;

	private final AuthorityService authorityService;

	public ResponseEntity<Object> addStudent(@Valid StudentAddRequest add, BindingResult br) {
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, null);
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Student student = new Student();
		mapper.map(add, student);
		String raw = add.getPassword();
		String pass = encoder.encode(raw);
		student.setPassword(pass);
		repository.save(student);
		Authority authority = new Authority();
		authority.setUsername(student.getUsername());
		authority.setAuthority("ROLE_STUDENT");
		authorityService.save(authority);

		// Response
		StudentAddResponse response = new StudentAddResponse();
		mapper.map(student, response);

		return ResponseEntity.ok(response);
	}

}
