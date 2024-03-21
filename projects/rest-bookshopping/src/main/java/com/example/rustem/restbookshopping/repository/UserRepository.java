package com.example.rustem.restbookshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rustem.restbookshopping.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	User username(String a);

}
