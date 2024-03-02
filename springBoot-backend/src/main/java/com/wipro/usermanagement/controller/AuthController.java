package com.wipro.usermanagement.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.usermanagement.constants.RoleType;
import com.wipro.usermanagement.dto.UserDto;
import com.wipro.usermanagement.exception.UserAuthenticationException;
import com.wipro.usermanagement.request.UserCredentialsRequest;
import com.wipro.usermanagement.service.UserService;
import com.wipro.usermanagement.constants.CommonConstants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Controller class for login and session management
 * 
 * @author Suraj G
 *
 */
@CrossOrigin(methods = { RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST }, origins = {
		"${front.end.host}" }, allowCredentials = "${allow.credentials}")
@RestController
@RequestMapping("/api/v1/token")
public class AuthController {

	@Autowired
	private UserService userService;

	/**
	 * Generate token
	 * 
	 * @param userRequest {@link UserRequest}
	 * @return Returns role {@link RoleType}
	 * @throws UserAuthenticationException
	 */
	@PostMapping("/generate")
	public RoleType generateToken(@RequestBody UserCredentialsRequest userCredentials, HttpServletRequest request)
			throws UserAuthenticationException {
		UserDto userDto = userService.getAuthenticatUser(userCredentials);
		request.getSession().setAttribute(CommonConstants.ACCESS_LEVEL_ATTR, userDto.getRole());	//proving Key-Value pairs for setting an attribute.
		return userDto.getRole();
	}

	/**
	 * Invalidates the session
	 * 
	 * @param request
	 */
	@GetMapping("/invalidate")
	public void destroySession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (null != session) {
			session.invalidate();
		}

	}

	/**
	 * Check if current session is active or not
	 * 
	 * @param request
	 * @return boolean
	 */
	@GetMapping("/isSessionActive")
	public boolean isSessionActive(HttpServletRequest request) {
		HttpSession existingSession = request.getSession(false);
		if (Objects.nonNull(existingSession)
				&& Objects.nonNull(existingSession.getAttribute(CommonConstants.ACCESS_LEVEL_ATTR))) {
			return true;
		}
		return false;
	}

}
