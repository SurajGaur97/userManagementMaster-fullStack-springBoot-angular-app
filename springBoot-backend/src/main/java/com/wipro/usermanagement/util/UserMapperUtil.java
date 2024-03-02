package com.wipro.usermanagement.util;

import com.wipro.usermanagement.dto.UserDto;
import com.wipro.usermanagement.entity.User;
import com.wipro.usermanagement.request.UserRequest;
import com.wipro.usermanagement.response.UserResponse;

import lombok.experimental.UtilityClass;

/**
 * An utility class for user mapping
 * 
 * @author Suraj G
 *
 */
@UtilityClass
public class UserMapperUtil {

	/**
	 * Map User to UserDto
	 * 
	 * @param user {@link User}
	 * @return userDto {@link UserDto}
	 */
	public UserDto mapToUserDto(User user) {
		return UserDto.builder().id(user.getId()).userName(user.getUserName()).fullName(user.getFullName())
				.emailAddress(user.getEmailAddress()).status(user.getStatus()).pass(user.getPass())
				.role(user.getRole().getName()).build();
	}

	/**
	 * Map UserRequest to UserDto
	 * 
	 * @param user {@link UserRequest}
	 * @return userDto {@link UserDto}
	 */
	public UserDto mapToUserDto(UserRequest userRequest) {
		return UserDto.builder().userName(userRequest.getUserName()).fullName(userRequest.getFullName())
				.emailAddress(userRequest.getEmailAddress()).status(userRequest.getStatus()).role(userRequest.getRole())
				.build();
	}

	/**
	 * Map UserDto to User
	 * 
	 * @param userDto {@link UserDto}
	 * @return user {@link User}
	 */
	public User mapToUser(UserDto userDto) {
		return User.builder().id(userDto.getId()).userName(userDto.getUserName()).fullName(userDto.getFullName())
				.emailAddress(userDto.getEmailAddress()).status(userDto.getStatus()).pass(userDto.getPass()).build();
	}

	/**
	 * Map UserDto to UserResponse
	 * 
	 * @param userDto {@link UserDto}
	 * @return user {@link UserResponse}
	 */
	public UserResponse mapToUserResponse(UserDto userDto) {
		return UserResponse.builder().userName(userDto.getUserName()).fullName(userDto.getFullName())
				.emailAddress(userDto.getEmailAddress()).status(userDto.getStatus()).role(userDto.getRole()).build();
	}

}
