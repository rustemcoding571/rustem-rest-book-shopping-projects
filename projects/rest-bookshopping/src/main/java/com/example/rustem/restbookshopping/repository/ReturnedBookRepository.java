package com.example.rustem.restbookshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rustem.restbookshopping.entity.ReturnedBook;

public interface ReturnedBookRepository extends JpaRepository<ReturnedBook, Integer> {

}
