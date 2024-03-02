package com.wipro.usermanagement.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.usermanagement.constants.ErrorCode;
import com.wipro.usermanagement.constants.RoleType;
import com.wipro.usermanagement.entity.Role;
import com.wipro.usermanagement.exception.RoleServiceException;
import com.wipro.usermanagement.repo.RoleRepository;
import com.wipro.usermanagement.service.RoleService;

/**
 * Service class for role 
 * 
 * @author Suraj G
 *
 */
@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;

	/**
	 * Get role by role name
	 * 
	 * @param {@link RoleType}
	 * 
	 * @return {@link Role}
	 * @throws RoleServiceException 
	 */
	@Override
	public Role getRoleByName(RoleType roleType) throws RoleServiceException {
		Role role = roleRepository.findByName(roleType);
		if (Objects.isNull(role)) {
			throw new RoleServiceException("Role is not defined", ErrorCode.ROLE_IS_NOT_DEFINED);
		}
		return roleRepository.findByName(roleType);
	}
	
}
