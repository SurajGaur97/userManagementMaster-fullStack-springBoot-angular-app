package com.wipro.usermanagement.entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.wipro.usermanagement.constants.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User Database Entity
 * 
 * @author Suraj G
 *
 */
@Getter
@Setter
@Builder  
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
    private UUID id;
	@Column(nullable = false, unique = true, name = "user_name")
	private String userName;
	@Column(name = "full_name")
	private String fullName;
	@Column(nullable = false, unique = true, name = "email_address")
	private String emailAddress;
	@ManyToOne
	@JoinColumn(name="role", nullable=false)
	private Role role;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Status status;
	@Column
	private String pass;
}
