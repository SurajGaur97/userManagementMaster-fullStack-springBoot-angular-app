package com.wipro.usermanagement.request;

import com.wipro.usermanagement.constants.RoleType;
import com.wipro.usermanagement.constants.Status;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Request class for user
 * @author Suraj G
 *
 */
@Builder
@Getter
@Setter
public class UserRequest {
	private String userName;
	private String fullName;
	private String emailAddress;
	private RoleType role;
	private Status status;
}
