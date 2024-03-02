package com.wipro.usermanagement.exception;

import com.wipro.usermanagement.constants.ErrorCode;

import lombok.Getter;

/**
 * User Management Authentication Exception
 * 
 * @author Suraj G
 *
 */
public class UserAuthenticationException extends Exception {

	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	private ErrorCode errorCode;
	
	public UserAuthenticationException(String message) {
		super(message);
	}
	
	public UserAuthenticationException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public UserAuthenticationException(String message, ErrorCode errorCode, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}
	
	public UserAuthenticationException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
