package com.jobfit.server.support.event.otp;

import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OtpEventHandler {

	private final JavaMailSender javaMailSender;

	@Async
	@EventListener
	public void sendMail(OtpMailEvent event) {
		javaMailSender.send(event.getMessage());
	}
}
