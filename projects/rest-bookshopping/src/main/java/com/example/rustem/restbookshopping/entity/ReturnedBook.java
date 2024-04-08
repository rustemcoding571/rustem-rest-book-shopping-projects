package com.example.rustem.restbookshopping.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "returned_books")
@Getter
@Setter
public class ReturnedBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer bookId;
	private Integer whichStudentBack;
	private String bookName;
	private String studentName;
	@CreationTimestamp
	private Timestamp register;
}
