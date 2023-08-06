package com.bookstore.chapterverse.controller;

import com.bookstore.chapterverse.dbaccess.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	@Autowired
	private final EmailSenderService emailSenderService;
	

	public EmailController(EmailSenderService emailSenderService) {
		super();
		this.emailSenderService = emailSenderService;
	}


	/*@RequestMapping(method = RequestMethod.POST, path = "/verification", consumes = "application/json")
    public ResponseEntity<String> sendVerificationEmail(@RequestBody String email) {
        try {
// Extract email address from EmailRequestDTO
            String subject = "Verification Token";
            String body = "Your verification token is: " + emailService.generateVerificationToken();
            String status = emailService.sendSimpleMail(email, subject, body);
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error while Sending Mail");
        }
}*/
	@RequestMapping(method = RequestMethod.POST, path = "/verification", consumes = "application/json")
	public ResponseEntity<String> sendEmail(@RequestBody EmailService email) {
		String subject="Verification Token";
		String token=email.generateVerificationToken();
		String body = "Your verification token is: " +token ;
		System.out.print(body);
		this.emailSenderService.sendEmail(email.getTo(),subject,body);
		return ResponseEntity.ok(token);
		
	}
	
	
}
