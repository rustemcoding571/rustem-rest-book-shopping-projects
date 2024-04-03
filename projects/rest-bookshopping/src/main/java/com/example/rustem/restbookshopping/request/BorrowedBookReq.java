package com.example.rustem.restbookshopping.request;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class BorrowedBookReq {

    //    @ManyToOne
//    @JoinColumn(name = "student_id")
//    private Student student;
    private Integer studentId;
    //
//    @ManyToOne
//    @JoinColumn(name = "book_id")
//    private Book book;
    private Integer bookId;


    // Ek alanlar eklenebilir, örneğin ödünç alma tarihi, iade tarihi vb.


}

