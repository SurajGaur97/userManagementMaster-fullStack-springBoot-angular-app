package com.wipro.usermanagement.repo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.wipro.usermanagement.constants.RoleType;
import com.wipro.usermanagement.entity.Role;

public interface RoleRepository extends CrudRepository<Role, UUID> {
	Role findByName(RoleType name);
}
