package com.wipro.usermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wipro.usermanagement.constants.ErrorCode;
import com.wipro.usermanagement.entity.UserNameUniqueKey;
import com.wipro.usermanagement.exception.UserServiceException;
import com.wipro.usermanagement.repo.UserNameUniqueKeyRepository;
import com.wipro.usermanagement.service.UniqueKeyUserService;

/**
 * Service class for uniqueKey-user
 * 
 * @author Suraj G
 *
 */
@Service
public class UniqueKeyUserServiceImpl implements UniqueKeyUserService {

	@Autowired
	private UserNameUniqueKeyRepository userNameUniqueKeyRepository;

	/**
	 * Get UserNameUniqueKey
	 * 
	 * @param userName
	 * @param uniqueKey
	 * 
	 * @return {@link UserNameUniqueKey}
	 */
	@SuppressWarnings("deprecation")
	@Override
	public UserNameUniqueKey getUserNameUniqueKeyByUserNameAndUniqueKey(String userName, String uniqueKey)
			throws UserServiceException {
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(uniqueKey)) {
			throw new UserServiceException("userName or uniqueKey is missed", ErrorCode.MANDATORY_FIELD_MISSED);
		}
		return userNameUniqueKeyRepository.findByUserNameAndUniqueKey(userName, uniqueKey);
	}

	/**
	 * Insert iniqueKeyUserName
	 * 
	 * @param userNameUniqueKey
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void insertUserNameUniqueKey(UserNameUniqueKey userNameUniqueKey) throws UserServiceException {
		if (StringUtils.isEmpty(userNameUniqueKey.getUserName()) || StringUtils.isEmpty(userNameUniqueKey.getUniqueKey())) {
			throw new UserServiceException("userName or uniqueKey is missed", ErrorCode.MANDATORY_FIELD_MISSED);
		}
		userNameUniqueKeyRepository.save(userNameUniqueKey);
		
	}

}
