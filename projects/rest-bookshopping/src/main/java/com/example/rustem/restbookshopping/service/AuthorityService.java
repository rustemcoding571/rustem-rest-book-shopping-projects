package com.example.rustem.restbookshopping.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.rustem.restbookshopping.entity.Authority;
import com.example.rustem.restbookshopping.repository.AuthorityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorityService {

	private final AuthorityRepository repository;

	public void save(Authority authority) {
		repository.save(authority);

	}

	public void deleteByUsername(String username) {
		repository.deleteByUsername(username);

	}

	public Optional<Authority> findById(Integer authorityId) {
		Optional<Authority> o = repository.findById(authorityId);
		return o;
	}

}
