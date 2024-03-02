package com.wipro.usermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * POJO for mail
 * @author Suraj G
 *
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Mail {
	private String mailFrom;

	private String mailTo;

	private String mailCc;

	private String mailBcc;

	private String mailSubject;

	private String mailContent;

	private String contentType;

	public Mail() {
		contentType = "text/plain";
	}
}
