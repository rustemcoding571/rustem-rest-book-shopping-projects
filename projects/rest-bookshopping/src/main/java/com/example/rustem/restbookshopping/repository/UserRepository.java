package com.example.rustem.restbookshopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.rustem.restbookshopping.entity.User;

import jakarta.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

	User username(String a);

//	@Query(value = "UPDATE users SET username=?1 where username=?2", nativeQuery = true)
//	@Modifying
//	void saveByUser(String newUsername, String username);

	@Query(value = "delete from users where username=?1", nativeQuery = true)
	@Modifying
	void deleteByUsername(String username);

	Optional<User> findByUsername(String username);

}
