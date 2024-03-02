package com.wipro.usermanagement.exception;

import com.wipro.usermanagement.constants.ErrorCode;

import lombok.Getter;

/**
 * Role Service Exception
 * 
 * @author Suraj G
 *
 */
public class RoleServiceException extends Exception {

	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	private ErrorCode errorCode;

	public RoleServiceException(String message) {
		super(message);
	}
	
	public RoleServiceException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public RoleServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}
	


	public RoleServiceException(String message, ErrorCode errorCode, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}
}
