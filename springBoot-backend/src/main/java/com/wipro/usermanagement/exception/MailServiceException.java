package com.wipro.usermanagement.exception;

import com.wipro.usermanagement.constants.ErrorCode;

import lombok.Getter;

/**
 * Mail Service Exception
 * 
 * @author Suraj G
 *
 */
public class MailServiceException extends Exception {

	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	private ErrorCode errorCode;

	public MailServiceException(String message) {
		super(message);
	}
	
	public MailServiceException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public MailServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}
	


	public MailServiceException(String message, ErrorCode errorCode, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}
}
