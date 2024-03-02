package com.wipro.usermanagement.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.usermanagement.constants.RoleType;
import com.wipro.usermanagement.entity.Role;
import com.wipro.usermanagement.exception.RoleServiceException;
import com.wipro.usermanagement.service.RoleService;

/**
 * Helper class to authorize a request
 * 
 * @author Suraj G
 *
 */
@Service
public class AuthorizationHelper {
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * Check if role has create access or not
	 * @param accessLevel
	 * @return
	 * @throws RoleServiceException
	 */
	public boolean hasCreateAccess(String accessLevel) throws RoleServiceException {
		Role role = roleService.getRoleByName(RoleType.valueOf(accessLevel));
		if (role.getHasCreateAccess()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Check if role has create access or not
	 * @param accessLevel
	 * @return
	 * @throws RoleServiceException
	 */
	public boolean hasReadAccess(String accessLevel) throws RoleServiceException {
		Role role = roleService.getRoleByName(RoleType.valueOf(accessLevel));
		if (role.getHasReadAccess()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Check if role has create access or not
	 * @param accessLevel
	 * @return
	 * @throws RoleServiceException 
	 */
	public boolean hasDeleteAccess(String accessLevel) throws RoleServiceException {
		Role role = roleService.getRoleByName(RoleType.valueOf(accessLevel));
		if (role.getHasDeleteAccess()) {
			return true;
		}
		return false;
	}
}
