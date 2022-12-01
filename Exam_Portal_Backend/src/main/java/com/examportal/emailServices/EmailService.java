package com.examportal.emailServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;
	
	public void registrationEmail(String to) {
		System.out.println("Inside registrationEmail method");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("yourmail");
		message.setTo(to);
		String subject = "Registration Successful....";
		String text = "Hi, \n Thanks for registering with us.\n Exam Portal Project(Akshay Sonawane)";
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}
}
