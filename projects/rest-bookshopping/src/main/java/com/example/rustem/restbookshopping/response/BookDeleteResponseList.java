package com.example.rustem.restbookshopping.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookDeleteResponseList {

	private Integer id;
	private String name;
	private LocalDate dateOf;
	private String creatorUsername;
}
