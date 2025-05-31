package com.microservice.manufacture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.manufacture.model.Mail;

public interface EmailRepository extends JpaRepository<Mail, Long>{

}
