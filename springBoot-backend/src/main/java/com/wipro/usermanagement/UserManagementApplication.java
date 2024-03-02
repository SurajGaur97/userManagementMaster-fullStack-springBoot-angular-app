package com.wipro.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main spring boot application
 * 
 * @author Suraj G
 *
 */
@EnableJpaRepositories("com.wipro.usermanagement.repo") 
@EntityScan("com.wipro.usermanagement.entity")
@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

}
