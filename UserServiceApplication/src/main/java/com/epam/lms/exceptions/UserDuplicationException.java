package com.epam.lms.exceptions;

public class UserDuplicationException extends RuntimeException {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserDuplicationException(String message) {
	    super(message);
	  }
}
