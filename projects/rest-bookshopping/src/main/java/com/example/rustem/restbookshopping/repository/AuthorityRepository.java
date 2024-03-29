package com.example.rustem.restbookshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.rustem.restbookshopping.entity.Authority;

import jakarta.transaction.Transactional;

@Transactional
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

	@Query(value = "delete from authorities where username=?1", nativeQuery = true)
	@Modifying
	void deleteByUsername(String username);

}
