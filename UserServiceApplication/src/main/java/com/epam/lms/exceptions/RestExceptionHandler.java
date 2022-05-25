package com.epam.lms.exceptions;

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

	@ExceptionHandler(value = UserNotFoundException.class)
	public  ResponseEntity<Map<String, String>> handlesUserNotFoundException(UserNotFoundException userNotFoundException,HttpServletRequest request) {
		
		
		Map<String, String> response = new HashMap<>();

		response.put("service", "users");
		response.put("timestamp", new Date().toString());
		response.put("error", userNotFoundException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = UserDuplicationException.class)
	public ResponseEntity<Map<String, String>> handlesUserNotFoundException(UserDuplicationException userDuplicationException,HttpServletRequest request) {
		
		
		Map<String, String> response = new HashMap<>();

		response.put("service", "users");
		response.put("timestamp", new Date().toString());
		response.put("error",userDuplicationException.getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
