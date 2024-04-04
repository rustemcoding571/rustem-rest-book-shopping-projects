package com.example.rustem.restbookshopping.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.rustem.restbookshopping.entity.Authority;
import com.example.rustem.restbookshopping.entity.User;
import com.example.rustem.restbookshopping.exception.OurRuntimeException;
import com.example.rustem.restbookshopping.repository.UserRepository;
import com.example.rustem.restbookshopping.request.LibrarianAddRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;

	private final ModelMapper mapper;

	private final AuthorityService authorityService;

	public User username(String a) {
		User user = repository.username(a);
		if (user == null) {
			throw new OurRuntimeException(null, "bele bir user yoxdur");
		}
		return user;
	}

	public ResponseEntity<Object> add(@Valid LibrarianAddRequest add, BindingResult br) {
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, null);
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = new User();
		mapper.map(add, user);
		String raw = add.getPassword();
		String pass = encoder.encode(raw);
		user.setPassword(pass);
		user.setEnabled(1);
		repository.save(user);
		Authority authority = new Authority();
		authority.setAuthority("ROLE_ADMIN");
		authority.setUsername(user.getUsername());
		authorityService.save(authority);

		return ResponseEntity.ok(user);
	}

	public void addStudent(User users) {
		repository.save(users);

	}

//	public void saveByUser(String username, String username2) {
//		repository.saveByUser(username, username2);
//
//	}

	public void deleteByUsername(String username) {
		repository.deleteByUsername(username);

	}

	public Optional<User> findByUsername(String username) {
		Optional<User> o = repository.findByUsername(username);
		return o;
	}

	public Optional<User> findById(Integer userId) {
		Optional<User> o = repository.findById(userId);
		return o;
	}

}
