package com.wipro.usermanagement.service;

import com.wipro.usermanagement.constants.RoleType;
import com.wipro.usermanagement.entity.Role;
import com.wipro.usermanagement.exception.RoleServiceException;

/**
 * Interface for role service
 * @author Suraj G
 *
 */
public interface RoleService {
	
	public Role getRoleByName(RoleType roleType) throws RoleServiceException;
}
