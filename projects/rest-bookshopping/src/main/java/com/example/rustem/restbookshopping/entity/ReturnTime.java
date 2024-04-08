package com.example.rustem.restbookshopping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "return_time")
@Getter
@Setter
public class ReturnTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	private LocalDateTime timeBookPurchase;
//	private LocalDateTime whenReturnBook;
	private String givenBook;
	private String returnedBook;
	private String whichStudent;
}
