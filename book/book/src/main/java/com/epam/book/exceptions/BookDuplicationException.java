package com.epam.book.exceptions;

public class BookDuplicationException extends RuntimeException {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookDuplicationException(String message) {
	    super(message);
	  }
}
