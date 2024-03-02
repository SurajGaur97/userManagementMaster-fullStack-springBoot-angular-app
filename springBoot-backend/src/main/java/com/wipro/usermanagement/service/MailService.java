package com.wipro.usermanagement.service;

import com.wipro.usermanagement.exception.MailServiceException;
import com.wipro.usermanagement.model.Mail;

public interface MailService {
	public void sendEmail(Mail mail) throws MailServiceException;
}
