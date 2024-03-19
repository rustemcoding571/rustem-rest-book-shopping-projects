package com.example.rustem.restbookshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rustem.restbookshopping.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
