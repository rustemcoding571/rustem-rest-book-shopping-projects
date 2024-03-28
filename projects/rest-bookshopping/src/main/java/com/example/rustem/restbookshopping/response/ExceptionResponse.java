package com.example.rustem.restbookshopping.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {

	private String message;
	private List<ValidationResponse> validations;
}
