package com.woloxgram.sharedalbum.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.woloxgram.sharedalbum.util.exception.AccessTypeNotAllowed;
import com.woloxgram.sharedalbum.util.exception.AlreadyExistException;
import com.woloxgram.sharedalbum.util.exception.NotFoundException;
import com.woloxgram.sharedalbum.util.exception.NullFieldException;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
	
	private static final String CAUSE = "cause";
	private static final String MESSAGE = "message";

	@ExceptionHandler(value = {NotFoundException.class})
	public ResponseEntity<Object> notFound(NotFoundException excepcion, WebRequest request) {
		Map<String, String> response = new HashMap<>();
		response.put(MESSAGE, excepcion.getMessage());
		response.put(CAUSE, excepcion.getCause()==null?null:excepcion.getCause().getMessage());
        return handleExceptionInternal(excepcion, response, new HttpHeaders(), HttpStatus.NOT_FOUND, request);		
	}
	
	@ExceptionHandler(value = {AlreadyExistException.class})
	public ResponseEntity<Object> alreadyExist(AlreadyExistException excepcion, WebRequest request) {
		Map<String, String> response = new HashMap<>();
		response.put(MESSAGE, excepcion.getMessage());
		response.put(CAUSE, excepcion.getCause()==null?null:excepcion.getCause().getMessage());
        return handleExceptionInternal(excepcion, response, new HttpHeaders(), HttpStatus.CONFLICT, request);		
	}
	
	@ExceptionHandler(value = {AccessTypeNotAllowed.class, NullFieldException.class})
	public ResponseEntity<Object> notAllowedException(RuntimeException excepcion, WebRequest request) {
		Map<String, String> response = new HashMap<>();
		response.put(MESSAGE, excepcion.getMessage());
		response.put(CAUSE, excepcion.getCause()==null?null:excepcion.getCause().getMessage());
        return handleExceptionInternal(excepcion, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);		
	}
}
