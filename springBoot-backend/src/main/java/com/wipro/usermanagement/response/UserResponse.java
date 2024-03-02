package com.wipro.usermanagement.response;

import com.wipro.usermanagement.constants.RoleType;
import com.wipro.usermanagement.constants.Status;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * Response class for User
 * 
 * @author Suraj G
 *
 */
@Builder
@Getter
@Setter
public class UserResponse {
	private String userName;
	private String fullName;
	private String emailAddress;
	private RoleType role;
	private Status status;
}
