package com.example.rustem.restbookshopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.rustem.restbookshopping.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> CreatorUsername(String creatorUsername);

	@Query(value = "select * from books limit ?1,?2", nativeQuery = true)
	List<Book> findPagination(Integer begin, Integer length);

}
