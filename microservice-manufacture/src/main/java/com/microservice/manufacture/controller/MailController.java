package com.microservice.manufacture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.manufacture.model.Mail;
import com.microservice.manufacture.service.EmailService;

@RestController
@RequestMapping("/api/v1/manufacture/mail")
public class MailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping
	public void sendHTMLEmail(@RequestBody Mail mail) {
		emailService.sendHTMLEmail(mail);
	}
	
	@GetMapping
	public ResponseEntity<List<Mail>> getMails(){
		return ResponseEntity.ok(emailService.getMails());
	}
}
