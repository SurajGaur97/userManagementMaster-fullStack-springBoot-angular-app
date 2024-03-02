package com.wipro.usermanagement.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.usermanagement.config.Config;
import com.wipro.usermanagement.constants.CommonConstants;
import com.wipro.usermanagement.constants.ErrorCode;
import com.wipro.usermanagement.constants.Status;
import com.wipro.usermanagement.dto.UserDto;
import com.wipro.usermanagement.entity.UserNameUniqueKey;
import com.wipro.usermanagement.exception.MailServiceException;
import com.wipro.usermanagement.exception.RoleServiceException;
import com.wipro.usermanagement.exception.UserAuthenticationException;
import com.wipro.usermanagement.exception.UserAuthorizationException;
import com.wipro.usermanagement.exception.UserServiceException;
import com.wipro.usermanagement.exception.UserServiceInternalServerErrorException;
import com.wipro.usermanagement.exception.UserServiceResourceNotFoundException;
import com.wipro.usermanagement.helper.AuthorizationHelper;
import com.wipro.usermanagement.model.Mail;
import com.wipro.usermanagement.request.PasswordUpdateRequest;
import com.wipro.usermanagement.request.UserRequest;
import com.wipro.usermanagement.response.UserResponse;
import com.wipro.usermanagement.service.MailService;
import com.wipro.usermanagement.service.UniqueKeyUserService;
import com.wipro.usermanagement.service.UserService;
import com.wipro.usermanagement.util.KeyGeneratorUtil;
import com.wipro.usermanagement.util.UserMapperUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Controller for user entity
 * 
 * @author Suraj G
 *
 */
@CrossOrigin(methods = { RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST }, origins = {
		"${front.end.host}" }, allowCredentials = "${allow.credentials}")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;

	@Autowired
	private UniqueKeyUserService uniqueKeyUserService;

	@Autowired
	private AuthorizationHelper authorizationHelper;

	@Autowired
	private Config config;

	@GetMapping
	public List<UserResponse> getUsers(HttpServletRequest request)
			throws RoleServiceException, UserAuthorizationException, UserAuthenticationException {
		HttpSession session = request.getSession(false);
		validateAuth(session);
		Object accessLevel = session.getAttribute(CommonConstants.ACCESS_LEVEL_ATTR);
		if (!authorizationHelper.hasReadAccess(accessLevel.toString())) {
			throw new UserAuthorizationException("User is not authroized", ErrorCode.AUTHORIZATION_FAILED);
		}
		return userService.getUsers().stream().map(userDto -> UserMapperUtil.mapToUserResponse(userDto))
				.collect(Collectors.toList());
	}

	@GetMapping("/{userName}")
	public UserResponse getUserByUserName(@PathVariable String userName, HttpServletRequest request)
			throws UserServiceResourceNotFoundException, RoleServiceException, UserAuthorizationException,
			UserAuthenticationException {
		HttpSession session = request.getSession(false);
		validateAuth(session);
		Object accessLevel = session.getAttribute(CommonConstants.ACCESS_LEVEL_ATTR);
		if (!authorizationHelper.hasReadAccess(accessLevel.toString())) {
			throw new UserAuthorizationException("User is not authroized", ErrorCode.AUTHORIZATION_FAILED);
		}
		return UserMapperUtil.mapToUserResponse(userService.getUserByUserName(userName));
	}

	@PostMapping
	public void createUser(@RequestBody UserRequest userRequest, HttpServletRequest request)
			throws UserServiceException, UserServiceInternalServerErrorException, RoleServiceException,
			UserAuthorizationException, NoSuchAlgorithmException, MailServiceException, UserAuthenticationException {
		HttpSession session = request.getSession(false);
		validateAuth(session);
		Object accessLevel = session.getAttribute(CommonConstants.ACCESS_LEVEL_ATTR);
		if (!authorizationHelper.hasCreateAccess(accessLevel.toString())) {
			throw new UserAuthorizationException("User is not authroized", ErrorCode.AUTHORIZATION_FAILED);
		}
		userService.insertUser(UserMapperUtil.mapToUserDto(userRequest));
		String uniqueKey = KeyGeneratorUtil.generateUniqueKey();
		String userName = userRequest.getUserName();
		uniqueKeyUserService.insertUserNameUniqueKey(new UserNameUniqueKey(userName, uniqueKey));
		sendMail(getUniqueLink(userName, uniqueKey), userRequest.getEmailAddress());
	}

	private String getUniqueLink(String userName, String uniqueKey) {
		StringBuilder stringBuilder = new StringBuilder(config.getFrontEndHost());
		return stringBuilder.append(CommonConstants.FORWARD_SLASH).append(config.getFrontEndActivateApi())
				.append(CommonConstants.QUESTION_MARK).append("userName=").append(userName)
				.append(CommonConstants.AMPERSAND).append("uniqueKey=").append(uniqueKey).toString();

	}

	private void sendMail(String uniqueLink, String mailTo) throws MailServiceException {
		Mail mail = Mail.builder().mailFrom(config.getMailUserName()).mailTo(mailTo).mailSubject("Activate Account")
				.mailContent(uniqueLink).build();
		mailService.sendEmail(mail);
	}

	@DeleteMapping("/{userName}")
	public void deleteUserByUserName(@PathVariable String userName, HttpServletRequest request)
			throws UserServiceException, RoleServiceException, UserAuthorizationException, UserAuthenticationException {
		HttpSession session = request.getSession(false);
		validateAuth(session);
		Object accessLevel = session.getAttribute(CommonConstants.ACCESS_LEVEL_ATTR);
		if (!authorizationHelper.hasDeleteAccess(accessLevel.toString())) {
			throw new UserAuthorizationException("User is not authroized", ErrorCode.AUTHORIZATION_FAILED);
		}
		userService.deleteUserByUserName(userName);
	}

	@PutMapping
	public void updateUserPassAndStatus(@RequestBody PasswordUpdateRequest passwordUpdateRequest)
			throws UserServiceException, UserServiceResourceNotFoundException {
		UserNameUniqueKey userNameUniqueKey = uniqueKeyUserService.getUserNameUniqueKeyByUserNameAndUniqueKey(
				passwordUpdateRequest.getUserName(), passwordUpdateRequest.getUniqueKey());
		UserDto userDto = userService.getUserByUserName(userNameUniqueKey.getUserName());
		userDto.setStatus(Status.ACTIVE);
		userDto.setPass(DigestUtils.sha256Hex(passwordUpdateRequest.getPass()));
		userService.updateUser(userDto);
	}

	private void validateAuth(HttpSession session)
			throws UserAuthenticationException, RoleServiceException, UserAuthorizationException {
		if (null == session || null == session.getAttribute(CommonConstants.ACCESS_LEVEL_ATTR)) {
			throw new UserAuthenticationException("User is not authenticated!", ErrorCode.AUTHENTICATION_FAILED);
		}
	}

}
