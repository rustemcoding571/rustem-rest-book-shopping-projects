package com.example.rustem.restbookshopping.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BorrowedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @ManyToOne
//    @JoinColumn(name = "student_id")
//    private Student student;
    private Integer studentId;
//
//    @ManyToOne
//    @JoinColumn(name = "book_id")
//    private Book book;
    private Integer bookId;

//    @ManyToOne
//    @JoinColumn(name = "book_id")
//    private User creator;
    private String creatorUsername;

    // Ek alanlar eklenebilir, örneğin ödünç alma tarihi, iade tarihi vb.


}
