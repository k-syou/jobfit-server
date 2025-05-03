package com.jobfit.server.interfaces.api.user.request;

import static com.jobfit.server.support.exception.BusinessError.*;

import com.jobfit.server.service.user.UserEditCommand;

import lombok.Getter;

@Getter
public class UserEditRequest {

	private String newPassword;
	private String confirmPassword;

	public UserEditRequest(String newPassword, String confirmPassword) {
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}

	public UserEditCommand toCommand(Long userId) {

		if (newPassword == null || newPassword.isEmpty()) {
			throw USER_EDIT_NEW_PASSWORD_NULL_OR_EMPTY.exception();
		}

		if (!newPassword.equals(confirmPassword)) {
			throw USER_EDIT_CONFIRM_PASSWORD_NULL_OR_EMPTY.exception();
		}

		return new UserEditCommand(userId, newPassword);
	}
}
