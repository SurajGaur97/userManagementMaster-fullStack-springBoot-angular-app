package com.wipro.usermanagement.repo;

import org.springframework.data.repository.CrudRepository;

import com.wipro.usermanagement.entity.UserNameUniqueKey;

public interface UserNameUniqueKeyRepository extends CrudRepository<UserNameUniqueKey, String> {
	UserNameUniqueKey findByUserNameAndUniqueKey(String userName, String uniqueKey);
	
	@SuppressWarnings("unchecked")
	UserNameUniqueKey save(UserNameUniqueKey userNameUniqueKey); 
	
	void deleteByUserName(String uername);
}
