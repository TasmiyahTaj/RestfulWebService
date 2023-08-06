package com.bookstore.chapterverse.dbaccess;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class Author {
private int authorID;
private String authorName;
private String authorEmail;
private String authorPwd;
private String authorPhone;
private String authorProfile;
private String Description;
public Author() {}
public Author(int authorID, String authorName, String authorEmail, String authorPwd, String authorPhone,
		String authorProfile,String Description) {
	super();
	this.authorID = authorID;
	this.authorName = authorName;
	this.authorEmail = authorEmail;
	this.authorPwd = authorPwd;
	this.authorPhone = authorPhone;
	this.authorProfile = authorProfile;
	this.Description = Description;
}
public String getDescription() {
	return Description;
}
public void setDescription(String description) {
	Description = description;
}
public int getAuthorID() {
	return authorID;
}
public void setAuthorID(int authorID) {
	this.authorID = authorID;
}
public String getAuthorName() {
	return authorName;
}
public void setAuthorName(String authorName) {
	this.authorName = authorName;
}
public String getAuthorEmail() {
	return authorEmail;
}
public void setAuthorEmail(String authorEmail) {
	this.authorEmail = authorEmail;
}
public String getAuthorPwd() {
	return authorPwd;
}
public void setAuthorPwd(String authorPwd) {
	this.authorPwd = authorPwd;
}
public String getAuthorPhone() {
	return authorPhone;
}
public void setAuthorPhone(String authorPhone) {
	this.authorPhone = authorPhone;
}
public String getAuthorProfile() {
	return authorProfile;
}
public void setAuthorProfile(String authorProfile) {
	this.authorProfile = authorProfile;
}
private JavaMailSender mailSender;

public void sendEmail(String to, String subject, String body) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(body);

    mailSender.send(message);
}
public String generateVerificationToken() {
    int verificationNumber = (int) (Math.random() * 1000000); // Generate a number between 0 and 999999
    return String.format("%06d", verificationNumber); 
  // Format as 6-digit with leading zeros if needed
}
}
