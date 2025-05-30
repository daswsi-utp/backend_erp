package com.microservice.manufacture.service.impl;

import java.util.List;

import org.springframework.mail.javamail.JavaMailSender;

import com.microservice.manufacture.model.Mail;
import com.microservice.manufacture.service.EmailService;

public class EmailServiceImpl implements EmailService {
	
	private final JavaMailSender mailSender;
	
	public EmailServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void sendHTMLEmail(Mail mail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendSimpleEmail(Mail mail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmailWithThymeLeaf(Mail mail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmailWithAttachment(Mail mail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Mail saveEmail(Mail mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mail> getMails() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
