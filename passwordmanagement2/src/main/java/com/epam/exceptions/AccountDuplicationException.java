package com.epam.exceptions;

public class AccountDuplicationException extends RuntimeException{


	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountDuplicationException(String message) {
	    super(message);
	  }
}
