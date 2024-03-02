package com.wipro.usermanagement.service;

import java.util.List;

import com.wipro.usermanagement.dto.UserDto;
import com.wipro.usermanagement.exception.UserAuthenticationException;
import com.wipro.usermanagement.exception.UserServiceException;
import com.wipro.usermanagement.exception.UserServiceInternalServerErrorException;
import com.wipro.usermanagement.exception.UserServiceResourceNotFoundException;
import com.wipro.usermanagement.request.UserCredentialsRequest;

/**
 * Interface for user service
 * 
 * @author Suraj G
 *
 */
public interface UserService {
	public List<UserDto> getUsers();

	public UserDto getUserByUserName(String userName) throws UserServiceResourceNotFoundException;

	public UserDto insertUser(UserDto userDto) throws UserServiceException, UserServiceInternalServerErrorException;
	
	public void deleteUserByUserName(String userName) throws UserServiceException;
	
	public UserDto getAuthenticatUser(UserCredentialsRequest userCredentials) throws UserAuthenticationException;

	void updateUser(UserDto userDto);
}
