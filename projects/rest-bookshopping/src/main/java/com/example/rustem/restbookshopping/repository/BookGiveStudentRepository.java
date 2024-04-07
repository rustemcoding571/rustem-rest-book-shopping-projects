package com.example.rustem.restbookshopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rustem.restbookshopping.entity.BookGiveStudent;

public interface BookGiveStudentRepository extends JpaRepository<BookGiveStudent, Long> {

	List<BookGiveStudent> findAllByFromWhom(String fromWhom);
}
