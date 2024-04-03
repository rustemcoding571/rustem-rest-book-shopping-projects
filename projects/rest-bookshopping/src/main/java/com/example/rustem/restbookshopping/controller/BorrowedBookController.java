package com.example.rustem.restbookshopping.controller;

import com.example.rustem.restbookshopping.entity.BorrowedBook;
import com.example.rustem.restbookshopping.repository.BookRepository;
import com.example.rustem.restbookshopping.repository.BorrowedBookRepository;
import com.example.rustem.restbookshopping.repository.StudentRepository;
import com.example.rustem.restbookshopping.request.BookAddRequest;
import com.example.rustem.restbookshopping.request.BorrowedBookReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/borrowedBooks")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BorrowedBookController{
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;
    private final BorrowedBookRepository borrowedBookRepository;

    @PostMapping
    public void add(@Valid @RequestBody BorrowedBookReq bookReq, BindingResult br) {

        BorrowedBook borrowedBook = new BorrowedBook();
//
        String username = SecurityContextHolder.getContext().getAuthentication().getName();// Request atanin username
        borrowedBook.setCreatorUsername(username);// Rustyem atibsa bura avtomatok

        //if there is book with this id
        Integer book_id = bookReq.getBookId() ;

        bookRepository.findById(book_id).orElseThrow(()-> new RuntimeException("There is no book with this id:" + book_id));

        borrowedBook.setBookId(book_id);
        //if there is student with this id

        // eynisi
        Integer student_id = bookReq.getStudentId() ;
        studentRepository.findById(student_id).orElseThrow(()-> new RuntimeException("There is no student with this id:" + student_id));

        borrowedBook.setStudentId(student_id);


        borrowedBookRepository.save(borrowedBook);

    }
}
