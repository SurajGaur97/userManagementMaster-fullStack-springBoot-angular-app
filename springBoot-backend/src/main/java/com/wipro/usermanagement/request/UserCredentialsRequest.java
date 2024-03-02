package com.wipro.usermanagement.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Credentials dto
 * 
 * @author Suraj G
 *
 */
@Getter
@Setter
public class UserCredentialsRequest {
	private String emailAddress;
	private String pass;

}
