package com.wipro.usermanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;

/**
 * Config class to read property
 * 
 * @author Suraj G
 *
 */
@Configuration
@Getter
public class Config {
	@Value("${front.end.host}")
	private String frontEndHost;
	
	@Value("${front.end.activate.api}")
	private String frontEndActivateApi;
	
	@Value("${mail.app.name}")
	private String mailAppName;
	
	@Value("${spring.mail.username}")
	private String mailUserName;
}
