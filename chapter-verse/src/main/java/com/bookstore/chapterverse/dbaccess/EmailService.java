package com.bookstore.chapterverse.dbaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	public String generateVerificationToken() {
		int verificationNumber = (int) (Math.random() * 1000000); // Generate a number between 0 and 999999
		return String.format("%06d", verificationNumber); // Format as 6-digit with leading zeros if needed
	}

	public String sendSimpleMail(String to, String subject, String body) {

		// Try block to check for exceptions
		try {

			// Creating a simple mail message
			SimpleMailMessage mailMessage = new SimpleMailMessage();

			// Setting up necessary details
			mailMessage.setFrom(sender);
			System.out.println(sender);
			mailMessage.setTo(to);
			mailMessage.setText(body);
			mailMessage.setSubject(subject);

			// Sending the mail
			javaMailSender.send(mailMessage);
			return "Mail Sent Successfully...";
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			return "Error while Sending Mail"+ e;
		}
	}
}
