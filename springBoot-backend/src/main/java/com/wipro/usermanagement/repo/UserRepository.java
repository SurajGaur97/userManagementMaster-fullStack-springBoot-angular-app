package com.wipro.usermanagement.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.wipro.usermanagement.entity.User;

public interface UserRepository extends CrudRepository<User, UUID> {
	List<User> findAll();
	User findByUserName(String userName);
	@SuppressWarnings("unchecked")
	User save(User user);
	void deleteByUserName(String userName);
	User findByEmailAddressAndPass(String emailAddress, String pass);
	boolean existsByUserName(String userName);
	boolean existsByEmailAddress(String emailAddress);
}
