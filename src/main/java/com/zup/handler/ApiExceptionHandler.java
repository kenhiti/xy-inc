package com.zup.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zup.exceptions.BusinessException;
import com.zup.exceptions.ResourceNotFoundException;
import com.zup.exceptions.utils.ErrorDetails;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> resourceNotFoundExceptionHandler(ResourceNotFoundException e, HttpServletRequest request) {
		ErrorDetails erro = new ErrorDetails();
		erro.setStatus(404l);
		erro.setTitulo(e.getMessage());
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorDetails> businessExceptionHandler(BusinessException e, HttpServletRequest request) {
		ErrorDetails erro = new ErrorDetails();
		erro.setStatus(500l);
		erro.setTitulo(e.getMessage());
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
	}
}
