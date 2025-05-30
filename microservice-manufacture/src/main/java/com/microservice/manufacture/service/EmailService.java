package com.microservice.manufacture.service;

import java.util.List;

import com.microservice.manufacture.model.Mail;

public interface EmailService {

	void sendHTMLEmail(Mail mail);
	
	void sendSimpleEmail(Mail mail);
	
	void sendEmailWithThymeLeaf(Mail mail);
	
	void sendEmailWithAttachment(Mail mail);
	
	Mail saveEmail(Mail mail);
	
	List<Mail> getMails();
}
