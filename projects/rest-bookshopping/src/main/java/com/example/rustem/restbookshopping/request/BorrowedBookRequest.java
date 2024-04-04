package com.example.rustem.restbookshopping.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowedBookRequest {

	private Integer bookId;
	private Integer studentId;
	private String fromWhom;

}
