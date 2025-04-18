package com.jobfit.server.interfaces.api.user;

import static com.jobfit.server.support.exception.BusinessError.*;

import com.jobfit.server.domain.user.UserStatus;
import com.jobfit.server.service.user.UserSignUpCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class UserSignupRequest {
	private String email;
	private String username;
	private String password;
	private String confirmPassword;
	private String nickname;
	UserStatus status;
	public UserSignupRequest() {}

	public UserSignUpCommand toCommand() {

		if (!password.equals(confirmPassword)) {
			throw USER_SIGNUP_PASSWORD_NOT_MATCH.exception();
		}

		return new UserSignUpCommand(email,username, password,nickname,status);
	}
}
