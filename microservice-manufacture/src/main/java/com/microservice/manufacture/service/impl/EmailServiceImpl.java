package com.microservice.manufacture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

import com.microservice.manufacture.model.Mail;
import com.microservice.manufacture.repository.EmailRepository;
import com.microservice.manufacture.service.EmailService;

public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private EmailRepository emailRepository;
	
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
		return emailRepository.save(mail);
	}

	@Override
	public List<Mail> getMails() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
