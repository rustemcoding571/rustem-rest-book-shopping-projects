package com.example.rustem.restbookshopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rustem.restbookshopping.entity.BorrowedBook;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {

	List<BorrowedBook> findAllByFromWhom(String fromWhom);

}
