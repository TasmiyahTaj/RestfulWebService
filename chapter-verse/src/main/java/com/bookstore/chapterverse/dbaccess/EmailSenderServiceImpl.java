package com.bookstore.chapterverse.dbaccess;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
private final JavaMailSender mailSender;
EmailService email= new EmailService();
public EmailSenderServiceImpl(JavaMailSender mailSender) {
	this.mailSender=mailSender;
}
	@Override
	public void sendEmail(String to,String Subject,String body) {
		// TODO Auto-generated method stub
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("forTestWeb321@gmail.com");
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(Subject);
		simpleMailMessage.setText(body);
		
		this.mailSender.send(simpleMailMessage);
	}

}
