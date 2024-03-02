package com.wipro.usermanagement.exception.handler;

import com.wipro.usermanagement.constants.ErrorCode;

import lombok.Getter;

/**
 * Error Response
 * 
 * @author Suraj G
 *
 */	
@Getter
public class ErrorResponse {
	private int status;
	private ErrorCode errorCode;
	private String message;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	

	public ErrorResponse(int status, ErrorCode errorCode, String message) {
		super();
		this.status = status;
		this.errorCode = errorCode;
		this.message = message;
	}
}
