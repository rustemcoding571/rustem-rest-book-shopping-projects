package com.example.rustem.restbookshopping.response;

import java.util.List;

import lombok.Data;

@Data
public class BookDeleteResponse {

	private List<BookDeleteResponseList> list;

	private String message;
}
