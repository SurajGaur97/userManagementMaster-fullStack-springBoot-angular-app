package com.wipro.usermanagement.service.impl;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.wipro.usermanagement.config.Config;
import com.wipro.usermanagement.constants.ErrorCode;
import com.wipro.usermanagement.exception.MailServiceException;
import com.wipro.usermanagement.model.Mail;
import com.wipro.usermanagement.service.MailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * Service class for mail
 * 
 * @author Suraj G
 *
 */
@Service
public class MailServiceImpl implements MailService {
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Config config;

	/**
	 * Send email
	 * 
	 * @param mail {@link Mail}
	 * @throws MailServiceException 
	 */
	public void sendEmail(Mail mail) throws MailServiceException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setSubject(mail.getMailSubject());
			mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), config.getMailAppName()));
			mimeMessageHelper.setTo(mail.getMailTo());
			mimeMessageHelper.setText(mail.getMailContent());

			mailSender.send(mimeMessageHelper.getMimeMessage());

		} catch (MessagingException | UnsupportedEncodingException cause) {
			throw new MailServiceException("Mail not sent", ErrorCode.INTERNAL_SERVER_ERROR, cause);
		}
	}
}
