package com.example.rustem.restbookshopping.exception;

import org.springframework.validation.BindingResult;

import lombok.Getter;

@Getter
public class OurRuntimeException extends RuntimeException {

	private BindingResult br;

	// xetani tutur get metodu ile gonderir handler-a cunki o buradan isteyir.
	public OurRuntimeException(BindingResult br, String m) {
		super(m);
		this.br = br;
	}

}
