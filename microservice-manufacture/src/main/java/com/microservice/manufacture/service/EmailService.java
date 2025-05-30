package com.microservice.manufacture.service;

import com.microservice.manufacture.model.Mail;

public interface EmailService {

	void sendHTMLEmail(Mail mail);
}
