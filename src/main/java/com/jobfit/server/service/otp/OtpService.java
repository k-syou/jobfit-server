package com.jobfit.server.service.otp;

import static com.jobfit.server.support.exception.BusinessError.*;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobfit.server.domain.otp.Otp;
import com.jobfit.server.domain.otp.OtpRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpService {

	private final OtpRepository otpRepository;
	private final JavaMailSender javaMailSender;

	@Transactional
	public void otpCreate(OtpCreateCommand command) {

		String otpNumber = createNumber();
		Otp otp = Otp.create(command.getEmail(), otpNumber);

		MimeMessage message = CreateMail(command.getEmail(), otpNumber);

		javaMailSender.send(message);
		otpRepository.save(otp);
	}

	@Transactional
	public void checkOtp(OtpCheckCommand command) {

		Otp otp = otpRepository.findByEmailAndOtp(command.getEmail(), command.getOtp())
			.orElseThrow(NOT_FOUND_OTP_ERROR::exception);

		otp.verify();
	}

	private MimeMessage CreateMail(String mail, String otpNumber){
		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			message.setFrom("whdtntn97@gmail.com");
			message.setRecipients(MimeMessage.RecipientType.TO, mail);
			message.setSubject("잡핏 회원가입 인증 OTP");
			String body = "";
			body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
			body += "<h1>" + otpNumber + "</h1>";
			body += "<h3>" + "감사합니다." + "</h3>";
			message.setText(body,"UTF-8", "html");
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return message;
	}

	private String createNumber() {
		return String.valueOf((int)(Math.random() * (90000)) + 100000);
	}
}
