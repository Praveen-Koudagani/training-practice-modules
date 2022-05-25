package com.epam.lms.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import feign.FeignException;



@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value = NotFoundException.class)
	public  ResponseEntity<Map<String, String>> handlesNotFoundException(NotFoundException notFoundException,HttpServletRequest request) {
		
		
		Map<String, String> response = new HashMap<>();
		response.put("timestamp", new Date().toString());
		response.put("error", notFoundException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = BookAlreadyIssuedException.class)
	public  ResponseEntity<Map<String, String>> handlesBookAlreadyIssuedException(BookAlreadyIssuedException bookAlreadyIssuedException,HttpServletRequest request) {
		
		
		Map<String, String> response = new HashMap<>();

		response.put("service", "users");
		response.put("timestamp", new Date().toString());
		response.put("error", bookAlreadyIssuedException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = BookLimitExceededException.class)
	public  ResponseEntity<Map<String, String>> handlesBookLimitExceededException(BookLimitExceededException bookLimitExceededException,HttpServletRequest request) {
		
		
		Map<String, String> response = new HashMap<>();

		response.put("service", "users");
		response.put("timestamp", new Date().toString());
		response.put("error",bookLimitExceededException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}

	/*
	 * @ExceptionHandler(value = HttpClientErrorException.class) public
	 * ResponseEntity<Map<String, String>>
	 * handlesHttpClientErrorException(HttpClientErrorException
	 * httpClientErrorException,HttpServletRequest request) {
	 * 
	 * 
	 * Map<String, String> response = new HashMap<>(); response.put("timestamp", new
	 * Date().toString());
	 * response.put("error",httpClientErrorException.getMessage()); return new
	 * ResponseEntity<>(response, httpClientErrorException.getStatusCode()); }
	 */
	@ExceptionHandler(value = FeignException.class)
	public  ResponseEntity<Map<String, String>> handlesHttpClientErrorException(FeignException feignException,HttpServletRequest request) {
		
		
		Map<String, String> response = new HashMap<>();
		response.put("timestamp", new Date().toString());
		response.put("error",feignException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.valueOf(feignException.status()));
	}
	
}
