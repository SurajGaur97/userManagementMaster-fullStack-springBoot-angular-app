package com.wipro.usermanagement.entity;

import com.wipro.usermanagement.constants.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Role entity
 * 
 * @author Suraj G
 *
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
	@Id
	private Integer id;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private RoleType name;
	@Column
	private Boolean hasReadAccess;
	@Column
	private Boolean hasCreateAccess;
	@Column
	private Boolean hasDeleteAccess;
}
