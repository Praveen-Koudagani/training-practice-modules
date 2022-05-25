package com.epam.lms.exceptions;

public class BookLimitExceededException extends RuntimeException {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookLimitExceededException(String message) {
	    super(message);
	  }

}
