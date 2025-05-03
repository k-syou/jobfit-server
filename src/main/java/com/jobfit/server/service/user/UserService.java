package com.jobfit.server.service.user;

import static com.jobfit.server.support.exception.BusinessError.*;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobfit.server.domain.otp.Otp;
import com.jobfit.server.domain.otp.OtpRepository;
import com.jobfit.server.domain.user.User;
import com.jobfit.server.domain.user.UserRepository;
import com.jobfit.server.support.event.otp.OtpMailEvent;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {


	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final SecureRandom random = new SecureRandom();

	private final OtpRepository otpRepository;
	private final UserRepository userRepository;
	private final JavaMailSender javaMailSender;
	private final PasswordEncoder passwordEncoder;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public UserInfo signUp(UserSignUpCommand command) {

		userRepository.findByUsername(command.getUsername())
			.ifPresent(user -> { throw USER_USERNAME_DUPLICATE_ERROR.exception(); });

		userRepository.findByEmail(command.getEmail())
			.ifPresent(user -> { throw USER_EMAIL_DUPLICATE_ERROR.exception(); });

		Otp otp = otpRepository.findByEmailAndOtp(command.getEmail(), command.getOtp())
			.orElseThrow(NOT_FOUND_OTP_ERROR::exception);

		User user = User.create(command.getEmail(), command.getUsername(), passwordEncoder.encode(command.getPassword()), command.getName());

		user.signUp(otp);

		userRepository.save(user);
		return UserInfo.from(user);
	}

	@Transactional
	public void isUsernameDuplicated(UserCheckDuplicatedEmailCommand command) {
		userRepository.findByUsername(command.getUsername())
			.ifPresent(user -> { throw USER_USERNAME_DUPLICATE_ERROR.exception(); });
	}

	@Transactional
	public void withDrawUser(UserWithDrawCommand command){
		User user = userRepository.findById(command.getUserId())
			.orElseThrow(USER_NOT_FOUND_ERROR::exception);

		user.withDraw();
	}

	@Transactional(readOnly = true)
	public UserInfo getProfile(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(USER_NOT_FOUND_ERROR::exception);
		return UserInfo.from(user);
	}

	@Transactional
	public void findUsername(FindUsernameCommand command) {

		Otp otp = otpRepository.findByEmailAndTypeAndOtp(command.getEmail(), command.getType(), command.getOtp())
			.orElseThrow(NOT_FOUND_OTP_ERROR::exception);

		User user = userRepository.findByEmail(command.getEmail())
			.orElseThrow(USER_NOT_FOUND_ERROR::exception);

		String username = user.findUsername(otp);

		MimeMessage message = CreateMail(command.getEmail(), username, "아이디 찾기");
		eventPublisher.publishEvent(new OtpMailEvent(message));

	}

	@Transactional
	public void findPassword(FindPasswordCommand command) {
		Otp otp = otpRepository.findByEmailAndTypeAndOtp(command.getEmail(), command.getType(), command.getOtp())
			.orElseThrow(NOT_FOUND_OTP_ERROR::exception);

		User user = userRepository.findByUsernameAndEmail(command.getUsername(), command.getEmail())
			.orElseThrow(USER_NOT_FOUND_ERROR::exception);

		String newPassword = createNewPassword();
		user.changePassword(otp, passwordEncoder.encode(newPassword));

		MimeMessage message = CreateMail(command.getEmail(), newPassword, "새로운 비밀번호");
		eventPublisher.publishEvent(new OtpMailEvent(message));
	}

	@Transactional
	public UserInfo editProfile(UserEditCommand command) {

		Long userId = command.getUserId();
		String newPassword = command.getNewPassword();

		User user = userRepository.findById(userId)
			.orElseThrow(USER_NOT_FOUND_ERROR::exception);

		user.changePassword(passwordEncoder.encode(newPassword));

		return UserInfo.from(user);
	}

	private String createNewPassword() {
		int length = random.nextInt(12 - 8 + 1) + 8;

		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
		}

		return sb.toString();
	}

	private MimeMessage CreateMail(String mail, String userinfo, String subject){
		MimeMessage message = javaMailSender.createMimeMessage();
		System.out.println(mail);
		System.out.println(userinfo);
		System.out.println(subject);
		try {
			message.setFrom(new InternetAddress("whdtntn97@gmail.com", "잡핏 운영팀"));
			message.setRecipients(MimeMessage.RecipientType.TO, mail);
			message.setSubject("잡핏 " + subject);
			String body = "";
			body += "<!DOCTYPE html>";
			body += "<html lang='ko'>";
			body += "<head>";
			body += "<meta charset='UTF-8'>";
			body += "<title>" + subject + "</title>";
			body += "</head>";
			body += "<body style='margin:0; padding:0; font-family:Arial, sans-serif; background-color:#f4f4f4;'>";
			body += "<table width='100%' bgcolor='#f4f4f4' cellpadding='0' cellspacing='0' border='0'>";
			body += "<tr>";
			body += "<td align='center' style='padding: 40px 0;'>";
			body += "<table width='600' cellpadding='0' cellspacing='0' border='0' bgcolor='#ffffff' style='border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1);'>";
			body += "<tr>";
			body += "<td style='padding: 40px 30px 20px; text-align: center;'>";
			body += "<h1 style='margin: 0; font-size: 24px; color: #333333;'>아이디 안내</h1>";
			body += "</td>";
			body += "</tr>";
			body += "<tr>";
			body += "<td style='padding: 0 30px 20px; text-align: center;'>";
			body += "<p style='margin: 0; font-size: 16px; color: #666666;'>요청하신 아이디는 아래와 같습니다.</p>";
			body += "</td>";
			body += "</tr>";
			body += "<tr>";
			body += "<td style='padding: 20px 30px; text-align: center;'>";
			body += "<div style='display: inline-block; background-color: #eeeeee; padding: 16px 24px; border-radius: 6px;'>";
			body += "<span style='font-size: 32px; color: #333333; letter-spacing: 4px; font-weight: bold;'>" + userinfo + "</span>";
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
}
