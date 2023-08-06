package com.bookstore.chapterverse.dbaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

public class EmailService {
	/*
	 * @Autowired private JavaMailSender javaMailSender;
	 * 
	 * @Value("${spring.mail.username}") private String sender;
	 * 
	 * public String generateVerificationToken() { int verificationNumber = (int)
	 * (Math.random() * 1000000); // Generate a number between 0 and 999999 return
	 * String.format("%06d", verificationNumber); // Format as 6-digit with leading
	 * zeros if needed }
	 * 
	 * public String sendSimpleMail(String to, String subject, String body) {
	 * 
	 * // Try block to check for exceptions try {
	 * 
	 * // Creating a simple mail message SimpleMailMessage mailMessage = new
	 * SimpleMailMessage();
	 * 
	 * // Setting up necessary details mailMessage.setFrom(sender);
	 * System.out.println(sender); mailMessage.setTo(to); mailMessage.setText(body);
	 * mailMessage.setSubject(subject);
	 * 
	 * // Sending the mail javaMailSender.send(mailMessage); return
	 * "Mail Sent Successfully..."; }
	 * 
	 * // Catch block to handle the exceptions catch (Exception e) { return
	 * "Error while Sending Mail"+ e; } } public void sendEmail(String to, String
	 * subject, String body) {}
	 */

	private String to;
	private String subject;
	private String body;

	public EmailService() {
	}

	public EmailService(String to, String subject, String body) {
		super();
		this.to = to;
		this.subject = subject;
		this.body = body;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	public String generateVerificationToken() {
	    // Generate a 6-digit random verification number
	    int verificationNumber = (int) (Math.random() * 900000) + 100000; // Generate a random number between 100000 and 999999

	    // Format the verification number as a 6-digit token
	    String verificationToken = String.format("%06d", verificationNumber);

	    return verificationToken;
	}

}
