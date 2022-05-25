package com.epam.lms.exceptions;

public class BookAlreadyIssuedException extends RuntimeException {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookAlreadyIssuedException(String message) {
	    super(message);
	  }
}
