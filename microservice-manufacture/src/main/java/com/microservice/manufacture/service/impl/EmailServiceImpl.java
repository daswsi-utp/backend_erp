package com.microservice.manufacture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

import com.microservice.manufacture.model.Mail;
import com.microservice.manufacture.repository.EmailRepository;
import com.microservice.manufacture.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private EmailRepository emailRepository;
	
	private final JavaMailSender mailSender;
	
	public EmailServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	@Async
	public void sendHTMLEmail(Mail mail) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		message.setFrom(new InternetAddress("eng27.26.25@gmail.com"));
        for (String recipient: mail.getTo()){
            message.addRecipients(MimeMessage.RecipientType.TO, recipient);
        }
        message.setSubject(mail.getSubject());
        message.setContent(mail.getBody(), "text/html; charset=utf-8");

        mailSender.send(message);
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
