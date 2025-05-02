package com.jobfit.server.service.otp;

import static com.jobfit.server.support.exception.BusinessError.*;

import java.io.UnsupportedEncodingException;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobfit.server.domain.otp.Otp;
import com.jobfit.server.domain.otp.OtpRepository;
import com.jobfit.server.domain.otp.OtpType;
import com.jobfit.server.support.event.otp.OtpMailEvent;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpService {

	private final OtpRepository otpRepository;
	private final JavaMailSender javaMailSender;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public void otpCreate(OtpCreateCommand command) {

		String otpNumber = createNumber();
		Otp otp = Otp.create(command.getEmail(), command.getType(), otpNumber);

		otpRepository.save(otp);
		eventPublisher.publishEvent(new OtpMailEvent(CreateMail(command.getEmail(), otpNumber, getEmailSubject(command.getType()))));
	}

	@Transactional
	public void checkOtp(OtpCheckCommand command) {

		Otp otp = otpRepository.findByEmailAndTypeAndOtp(command.getEmail(), command.getType(), command.getOtp())
			.orElseThrow(NOT_FOUND_OTP_ERROR::exception);

		otp.verify();
	}

	private String getEmailSubject(OtpType otpType) {
		if (otpType == OtpType.SIGNUP) {
			return "회원가입";
		}
		if (otpType == OtpType.USERNAME) {
			return "아이디 찾기";
		}
		if (otpType == OtpType.PASSWORD) {
			return "비밀번호 찾기";
		}
		return "회원가입";
	}

	private MimeMessage CreateMail(String mail, String otpNumber, String subject){
		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			message.setFrom(new InternetAddress("whdtntn97@gmail.com", "잡핏 운영팀"));
			message.setRecipients(MimeMessage.RecipientType.TO, mail);
			message.setSubject("잡핏 " + subject + " 인증 OTP");
			String body = "";
			body += "<!DOCTYPE html>";
			body += "<html lang='ko'>";
			body += "<head>";
			body += "<meta charset='UTF-8'>";
			body += "<title>OTP 인증번호</title>";
			body += "</head>";
			body += "<body style='margin:0; padding:0; font-family:Arial, sans-serif; background-color:#f4f4f4;'>";
			body += "<table width='100%' bgcolor='#f4f4f4' cellpadding='0' cellspacing='0' border='0'>";
			body += "<tr>";
			body += "<td align='center' style='padding: 40px 0;'>";
			body += "<table width='600' cellpadding='0' cellspacing='0' border='0' bgcolor='#ffffff' style='border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1);'>";
			body += "<tr>";
			body += "<td style='padding: 40px 30px 20px; text-align: center;'>";
			body += "<h1 style='margin: 0; font-size: 24px; color: #333333;'>인증번호 안내</h1>";
			body += "</td>";
			body += "</tr>";
			body += "<tr>";
			body += "<td style='padding: 0 30px 20px; text-align: center;'>";
			body += "<p style='margin: 0; font-size: 16px; color: #666666;'>요청하신 인증번호는 아래와 같습니다.</p>";
			body += "</td>";
			body += "</tr>";
			body += "<tr>";
			body += "<td style='padding: 20px 30px; text-align: center;'>";
			body += "<div style='display: inline-block; background-color: #eeeeee; padding: 16px 24px; border-radius: 6px;'>";
			body += "<span style='font-size: 32px; color: #333333; letter-spacing: 4px; font-weight: bold;'>" + otpNumber + "</span>";
			body += "</div>";
			body += "</td>";
			body += "</tr>";
			body += "<tr>";
			body += "<td style='padding: 0 30px 40px; text-align: center;'>";
			body += "<p style='margin: 0; font-size: 14px; color: #999999;'>본 인증번호는 5분간 유효합니다.</p>";
			body += "<p style='margin: 8px 0 0; font-size: 14px; color: #999999;'>감사합니다.</p>";
			body += "</td>";
			body += "</tr>";
			body += "</table>";
			body += "</td>";
			body += "</tr>";
			body += "</table>";
			body += "</body>";
			body += "</html>";
			message.setText(body,"UTF-8", "html");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return message;
	}

	private String createNumber() {
		return String.valueOf((int)(Math.random() * (90000)) + 100000);
	}
}
