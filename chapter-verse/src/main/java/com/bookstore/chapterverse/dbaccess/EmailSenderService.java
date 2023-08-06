package com.bookstore.chapterverse.dbaccess;

public interface EmailSenderService {
void sendEmail(String to, String subject, String body);
}
