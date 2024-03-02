package com.wipro.usermanagement.dto;

import java.util.UUID;

import com.wipro.usermanagement.constants.RoleType;
import com.wipro.usermanagement.constants.Status;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * DTO class for User
 * 
 * @author Suraj G
 *
 */
@Builder
@Getter
@Setter
public class UserDto {
	private UUID id;
	private String userName;
	private String fullName;
	private String emailAddress;
	private RoleType role;
	private Status status;
	private String pass;
}
