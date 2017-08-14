package com.zup.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String mensagem) {
		super(mensagem);
	}

	public ResourceNotFoundException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
