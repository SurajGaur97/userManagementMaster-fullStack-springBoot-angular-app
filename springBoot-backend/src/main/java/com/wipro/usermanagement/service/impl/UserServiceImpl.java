package com.wipro.usermanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wipro.usermanagement.constants.ErrorCode;
import com.wipro.usermanagement.constants.Status;
import com.wipro.usermanagement.dto.UserDto;
import com.wipro.usermanagement.entity.Role;
import com.wipro.usermanagement.entity.User;
import com.wipro.usermanagement.exception.UserAuthenticationException;
import com.wipro.usermanagement.exception.UserServiceException;
import com.wipro.usermanagement.exception.UserServiceInternalServerErrorException;
import com.wipro.usermanagement.exception.UserServiceResourceNotFoundException;
import com.wipro.usermanagement.repo.RoleRepository;
import com.wipro.usermanagement.repo.UserNameUniqueKeyRepository;
import com.wipro.usermanagement.repo.UserRepository;
import com.wipro.usermanagement.request.UserCredentialsRequest;
import com.wipro.usermanagement.service.UserService;
import com.wipro.usermanagement.util.UserMapperUtil;

/**
 * Service class for user
 * 
 * @author Suraj G
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserNameUniqueKeyRepository userNameUniqueKeyRepository;
	/**
	 * Get all users
	 * 
	 * 
	 * @return list of {@link UserDto}
	 */
	@Override
	public List<UserDto> getUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user -> UserMapperUtil.mapToUserDto(user)).collect(Collectors.toList());
	}

	/**
	 * Get user by userName
	 * 
	 * @param userName
	 * @return {@link UserDto}
	 * @throws UserServiceResourceNotFoundException
	 */
	@Override
	public UserDto getUserByUserName(String userName) throws UserServiceResourceNotFoundException {
		User user = userRepository.findByUserName(userName);
		if (Objects.isNull(user)) {
			throw new UserServiceResourceNotFoundException("User not found", ErrorCode.USER_NOT_FOUND);
		}
		return UserMapperUtil.mapToUserDto(user);
	}

	/**
	 * Insert user
	 * 
	 * @param userDto {@link UserDto}
	 * @return {@link UserDto}
	 * @throws UserServiceInternalServerErrorException
	 * @throws UserServiceException
	 */
	@Override
	public UserDto insertUser(UserDto userDto) throws UserServiceInternalServerErrorException, UserServiceException {
		validateUserDto(userDto);
		try {
			Role role = roleRepository.findByName(userDto.getRole());
			if (Objects.isNull(role)) {
				throw new UserServiceException("Role is not defined", ErrorCode.ROLE_IS_NOT_DEFINED);
			}
			userDto.setStatus(Status.PENDING);
			User user = UserMapperUtil.mapToUser(userDto);
			user.setRole(role);
			return UserMapperUtil.mapToUserDto(userRepository.save(user));
		} catch (Exception exception) {
			throw new UserServiceInternalServerErrorException("UserService: User creation is failed",
					ErrorCode.INTERNAL_SERVER_ERROR, exception);
		}
	}

	private void validateUserDto(UserDto userDto) throws UserServiceException {
		if (Objects.isNull(userDto.getUserName()) || Objects.isNull(userDto.getEmailAddress())
				|| Objects.isNull(userDto.getRole())) {
			throw new UserServiceException("Mandatory fiels are missed", ErrorCode.MANDATORY_FIELD_MISSED);
		}
		if (userRepository.existsByUserName(userDto.getUserName())) {
			throw new UserServiceException("User name already exists", ErrorCode.USER_NAME_ALREADY_EXISTS);
		}

		if (userRepository.existsByEmailAddress(userDto.getEmailAddress())) {
			throw new UserServiceException("Email already exists", ErrorCode.EMAIL_ALREADY_EXISTS);
		}
	}

	/**
	 * Delete user by userName
	 * 
	 * @throws UserServiceException
	 * @author Suraj G
	 */
	@SuppressWarnings("deprecation")
	@Transactional
	@Override
	public void deleteUserByUserName(String userName) throws UserServiceException {
		if (StringUtils.isEmpty(userName)) {
			throw new UserServiceException("UserService: User deletion is failed", ErrorCode.MANDATORY_FIELD_MISSED);
		}
		userRepository.deleteByUserName(userName);
	}

	/**
	 * Get user if user is authenticate
	 * 
	 * @param userCredentials {@link UserCredentialsRequest}
	 * 
	 * @return {@link UserDto}
	 *
	 * @throws UserAuthenticationException
	 * @author Suraj G
	 */
	@Override
	public UserDto getAuthenticatUser(UserCredentialsRequest userCredentials) throws UserAuthenticationException {
		String encodedPass = DigestUtils.sha256Hex(userCredentials.getPass());
		User user = userRepository.findByEmailAddressAndPass(userCredentials.getEmailAddress(), encodedPass);
		if (Objects.nonNull(user)) {
			return UserMapperUtil.mapToUserDto(user);
		}
		throw new UserAuthenticationException("User is not authenticated!", ErrorCode.AUTHENTICATION_FAILED);
	}

	/**
	 * Update user password
	 * 
	 * @param {@link UserDto}
	 */
	@Override
	@Transactional
	public void updateUser(UserDto userDto) {
		User user = UserMapperUtil.mapToUser(userDto);
		Role role = roleRepository.findByName(userDto.getRole());
		user.setRole(role);
		userRepository.save(user);
		userNameUniqueKeyRepository.deleteByUserName(userDto.getUserName());
	}

}
