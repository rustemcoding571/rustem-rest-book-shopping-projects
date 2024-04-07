package com.example.rustem.restbookshopping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "borrowed_books")
@Getter
@Setter
public class BorrowedBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer studentId;
	private String studentName;
	private Integer bookId;
	private String bookName;
	private String fromWhom;
	private String BookCreatorUsername;
	private String StudentCreatorUsername;
}
