package com.wipro.usermanagement.service;

import com.wipro.usermanagement.entity.UserNameUniqueKey;
import com.wipro.usermanagement.exception.UserServiceException;

/**
 * Interface for unique key-user service
 * 
 * @author Suraj G
 *
 */
public interface UniqueKeyUserService {
	public UserNameUniqueKey getUserNameUniqueKeyByUserNameAndUniqueKey(String userName, String uniqueKey)
			throws UserServiceException;

	public void insertUserNameUniqueKey(UserNameUniqueKey userNameUniqueKey) throws UserServiceException;
}
