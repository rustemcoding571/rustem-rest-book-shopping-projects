package com.example.rustem.restbookshopping.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.rustem.restbookshopping.exception.OurRuntimeException;
import com.example.rustem.restbookshopping.response.ExceptionResponse;
import com.example.rustem.restbookshopping.response.ValidationResponse;

@RestControllerAdvice
public class Handler {

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ExceptionResponse handle(OurRuntimeException e) {
		ExceptionResponse r = new ExceptionResponse();
		BindingResult br = e.getBr();
		if (br == null) {

		} else {
			List<FieldError> fieldErrors = br.getFieldErrors();
			List<ValidationResponse> validations = new ArrayList<>();

			for (FieldError error : fieldErrors) {
				ValidationResponse v = new ValidationResponse();
				v.setField(error.getField());
				v.setMessage(error.getDefaultMessage());
				validations.add(v);
			}
			r.setValidations(validations);
		}
		r.setMessage(e.getMessage());

		return r;

	}
}
