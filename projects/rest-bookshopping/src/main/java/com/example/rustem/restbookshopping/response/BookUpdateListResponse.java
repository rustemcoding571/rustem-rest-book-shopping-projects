package com.example.rustem.restbookshopping.response;

import lombok.Data;

@Data
public class BookUpdateListResponse {

	private Integer bookId;
	private String name;
	private Double price;
}
