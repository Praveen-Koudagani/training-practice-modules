package com.epam.exceptions;

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

	@ExceptionHandler(value = AccountNotFoundException.class)
	public  ResponseEntity<Map<String, String>> handlesAccountNotFoundException(AccountNotFoundException accountNotFoundException,HttpServletRequest request) {
		
		
		Map<String, String> response = new HashMap<>();

		response.put("service", "accounts");
		response.put("timestamp", new Date().toString());
		response.put("error", accountNotFoundException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = GroupNotFoundException.class)
	public ResponseEntity<Map<String, String>> handlesGroupNotFoundException(GroupNotFoundException groupNotFoundException,HttpServletRequest request) {
		
		
		Map<String, String> response = new HashMap<>();

		response.put("service", "group");
		response.put("timestamp", new Date().toString());
		response.put("error", groupNotFoundException.getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = AccountDuplicationException.class)
	public ResponseEntity<Map<String, String>> handlesAccountDuplicationException(AccountDuplicationException accountDuplicationException,HttpServletRequest request) {
		
		
		Map<String, String> response = new HashMap<>();

		response.put("service", "accounts");
		response.put("timestamp", new Date().toString());
		response.put("error", accountDuplicationException.getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}


}
