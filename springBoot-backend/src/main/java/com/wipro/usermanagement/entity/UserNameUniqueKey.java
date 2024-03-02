package com.wipro.usermanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entity to store key pair value of user name and unique key
 * 
 * @author Suraj G
 *
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserNameUniqueKey {
	@Id
	@Column(name = "user_name", nullable = false)
	private String userName;
	@Column(name = "unique_key", nullable = false)
	private String uniqueKey;
}
