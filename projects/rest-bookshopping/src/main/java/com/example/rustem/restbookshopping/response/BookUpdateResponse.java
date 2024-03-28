package com.example.rustem.restbookshopping.response;

import java.util.List;

import lombok.Data;

@Data
public class BookUpdateResponse {

	private String message;
	List<BookUpdateListResponse> list;

}
