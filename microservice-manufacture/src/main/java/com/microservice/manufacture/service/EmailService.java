package com.microservice.manufacture.service;

import com.microservice.manufacture.model.Mail;

public interface EmailService {

	void sendHTMLEmail(Mail mail);
	
	void sendSimpleEmail(Mail mail);
	
	void sendEmailWithThymeLeaf(Mail mail);
}
