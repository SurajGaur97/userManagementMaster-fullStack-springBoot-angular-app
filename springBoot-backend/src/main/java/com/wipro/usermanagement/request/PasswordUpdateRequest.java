package com.wipro.usermanagement.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Request class for password reset
 * 
 * @author Suraj G
 *
 */
@Getter
@Setter
public class PasswordUpdateRequest {
	private String userName;
	private String pass;
	private String uniqueKey;
}
