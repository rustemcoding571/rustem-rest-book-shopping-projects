package com.example.rustem.restbookshopping.repository;

import com.example.rustem.restbookshopping.entity.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
}
