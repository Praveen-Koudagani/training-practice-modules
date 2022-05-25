package com.epam.book.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value = BookNotFoundException.class)
	public  ResponseEntity<Map<String, String>> handlesBookNotFoundException(BookNotFoundException bookNotFoundException,HttpServletRequest request) {
		
		
		Map<String, String> response = new HashMap<>();

		response.put("service", "books");
		response.put("timestamp", new Date().toString());
		response.put("error", bookNotFoundException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = BookDuplicationException.class)
	public ResponseEntity<Map<String, String>> handlesBookDuplicationException(BookDuplicationException bookDuplicationException,HttpServletRequest request) {
		
		
		Map<String, String> response = new HashMap<>();

		response.put("service", "books");
		response.put("timestamp", new Date().toString());
		response.put("error",bookDuplicationException.getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}
}
